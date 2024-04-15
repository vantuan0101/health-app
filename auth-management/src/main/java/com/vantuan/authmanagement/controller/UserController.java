package com.vantuan.authmanagement.controller;

import com.vantuan.authmanagement.criteria.UserCriteria;
import com.vantuan.authmanagement.model.entity.User;
import com.vantuan.authmanagement.service.UserService;
import com.vantuan.common.exception.EntityNotFoundException;
import com.vantuan.common.exception.ValidationException;
import com.vantuan.crud.controller.BaseController;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "User")
@RestController
@RequestMapping(path = "/users")
public class UserController extends BaseController<User, UserCriteria> {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Transactional
    @GetMapping()
    public ResponseEntity<UserCriteria> findByCriteria(@RequestBody String email) throws EntityNotFoundException {
        User user = this.userService.findByEmail(email).orElseThrow();
        UserCriteria patientCriteria = this.userService.convertToDto(user);
        return ResponseEntity.ok(patientCriteria);
    }

    @Transactional
    @GetMapping("/{id}")
    public ResponseEntity<UserCriteria> findById(@PathVariable Long id) throws EntityNotFoundException {
        User user = this.userService.findById(id);
        UserCriteria patientCriteria = this.userService.convertToDto(user);
        return ResponseEntity.ok(patientCriteria);
    }

    @Transactional
    @PostMapping()
    public ResponseEntity<UserCriteria> save(@RequestBody UserCriteria dto) throws ValidationException {
        User patient = this.userService.save(dto);
        return ResponseEntity.ok(this.userService.convertToDto(patient));
    }

    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity<UserCriteria> update(@PathVariable Long id, @RequestBody UserCriteria dto)
            throws ValidationException {
        User patient = this.userService.update(id, dto);
        return ResponseEntity.ok(this.userService.convertToDto(patient));
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<UserCriteria> updateAll(@PathVariable Long id, @RequestBody UserCriteria dto)
            throws ValidationException {
        User patient = this.userService.updateAll(id, dto);
        return ResponseEntity.ok(this.userService.convertToDto(patient));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws EntityNotFoundException {
        this.userService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
