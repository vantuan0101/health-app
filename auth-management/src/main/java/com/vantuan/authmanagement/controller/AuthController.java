package com.vantuan.authmanagement.controller;

import com.vantuan.authmanagement.config.Constants;
import com.vantuan.authmanagement.config.JwtUtils;
import com.vantuan.authmanagement.config.UserDetailsImplement;
import com.vantuan.authmanagement.criteria.UserCriteria;
import com.vantuan.authmanagement.model.entity.User;
import com.vantuan.authmanagement.response.GenericResponse;
import com.vantuan.authmanagement.response.LoginResponse;
import com.vantuan.authmanagement.service.UserService;
import com.vantuan.common.exception.BadRequestException;
import com.vantuan.crud.controller.BaseController;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

@Slf4j
@Tag(name = "Auth")
@RestController
@RequestMapping(path = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController extends BaseController<User, UserCriteria> {

    @Autowired
    private AuthenticationManager authenticationManager;

    private UserService userService;

    private final BCryptPasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    public AuthController(UserService userService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = new ModelMapper();
    }

    @PostMapping(path = "/register")
    public GenericResponse addNewUser(@RequestBody @Valid UserCriteria user) {
        try {
            if (!user.getPassword().equals(user.getConfirmPass()))
                throw new BadRequestException(Constants.MESSAGE_INVALID_MATCH_PASSWORD);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.save(user);
            GenericResponse response = new GenericResponse(Constants.MESSAGE_REGISTER_WELCOME);
            return response;
        } catch (Exception ex) {
            throw new BadRequestException(ex.getMessage());
        }
    }

    @PostMapping(path = "/login")
    public LoginResponse login(@RequestBody @Valid UserCriteria loginRequest, HttpSession httpSession) {
        try {
            String password = loginRequest.getPassword();
            String email = loginRequest.getEmail();
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);
            UserDetailsImplement userDetails = (UserDetailsImplement) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());
            httpSession.setAttribute(UserService.USERID, userDetails.getId());
            LoginResponse response = new LoginResponse(
                    Constants.MESSAGE_LOGIN_SUCCESS,
                    jwt,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    userDetails.getFirstName(),
                    userDetails.getLastName(),
                    roles);
            return response;
        } catch (Exception ex) {
            throw new BadRequestException(ex.getMessage());
        }

    }

    @PostMapping(path = "/change-password")
    public ResponseEntity<String> changePassword(@RequestBody Map<String, String> request, HttpSession httpSession) {
        try {
            String userEmail = (String) httpSession.getAttribute(UserService.USER_EMAIL);
            if (userEmail == null) {
                throw new BadRequestException("User not authenticated");
            }
            String newPassword = request.get("newPassword");
            if (newPassword == null || newPassword.isEmpty()) {
                throw new BadRequestException("New password is required");
            }
            User user = userService.findByEmail(userEmail)
                    .orElseThrow(() -> new BadRequestException("User not found"));
            user.setPassword(passwordEncoder.encode(newPassword));
            userService.save(userService.convertToDto(user));
            Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), newPassword);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.ok().body("Password changed successfully");
        } catch (Exception ex) {
            throw new BadRequestException(ex.getMessage());
        }
    }

    @PostMapping(path = "/logout")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<String> logout(HttpSession httpSession) {
        try {
            httpSession.invalidate();
            return ResponseEntity.ok().body("Logout Successfully");
        } catch (Exception ex) {
            throw new BadRequestException(ex.getMessage());
        }

    }

}
