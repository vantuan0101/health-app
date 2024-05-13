package com.vantuan.authmanagement.controller;

import com.vantuan.authmanagement.config.Constants;
import com.vantuan.authmanagement.sercurity.JwtUtils;
import com.vantuan.authmanagement.config.UserDetailsImplement;
import com.vantuan.authmanagement.criteria.UserCriteria;
import com.vantuan.authmanagement.model.data.UserData;
import com.vantuan.authmanagement.model.entity.User;
import com.vantuan.authmanagement.response.GenericResponse;
import com.vantuan.authmanagement.response.LoginResponse;
import com.vantuan.authmanagement.service.UserService;
import com.vantuan.framework.common.exception.BadRequestException;
import com.vantuan.framework.common.mapper.MappingUtil;
import com.vantuan.framework.crud.controller.BaseController;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

@Slf4j
@Tag(name = "Auth")
@RestController
@RequestMapping(path = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AuthController extends BaseController<User, Long, UserCriteria> {

    @Autowired
    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final BCryptPasswordEncoder passwordEncoder;

    private final MappingUtil mappingUtil;

    @Autowired
    private JwtUtils jwtUtils;

    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/register")
    public GenericResponse addNewUser(@Nonnull @Valid @RequestBody final UserData.Create data) {
        try {
            if (!data.getPassword().equals(data.getConfirmPass()))
                throw new BadRequestException(Constants.MESSAGE_INVALID_MATCH_PASSWORD);
            data.setPassword(passwordEncoder.encode(data.getPassword()));
            userService.save(mappingUtil.map(data, User.class));
            GenericResponse response = new GenericResponse(Constants.MESSAGE_REGISTER_WELCOME);
            return response;
        } catch (Exception ex) {
            throw new BadRequestException(ex.getMessage());
        }
    }

    @Transactional
    @PostMapping(path = "/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse login(@Nonnull @Valid @RequestBody final UserData.Create loginRequest,
            HttpSession httpSession) {
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

    @Transactional
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
