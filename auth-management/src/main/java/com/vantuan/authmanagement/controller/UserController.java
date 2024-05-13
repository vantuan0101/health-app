package com.vantuan.authmanagement.controller;

import com.vantuan.authmanagement.criteria.UserCriteria;
import com.vantuan.authmanagement.model.data.UserData;
import com.vantuan.authmanagement.model.entity.User;
import com.vantuan.authmanagement.read.UserDTOs;
import com.vantuan.authmanagement.service.UserService;
import com.vantuan.common.mapper.MappingUtil;
import com.vantuan.crud.controller.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "User")
@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController extends BaseController<User, Long, UserCriteria> {

    private final MappingUtil mappingUtil;
    private final UserService userService;

    @Transactional
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get User")
    public ResponseEntity<UserDTOs.Full> findById(@PathVariable Long id) {
        log.info("Received get user request : {}");
        return new ResponseEntity<>(mappingUtil.map(userService.get(id), UserDTOs.Full.class), HttpStatus.OK);

    }

    @Transactional
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Register User")
    public ResponseEntity<UserDTOs.Full> register(@Nonnull @Valid @RequestBody final UserData.Create data) {
        log.info("Received register request for user : {}");
        return new ResponseEntity<>(mappingUtil.map(userService.create(data), UserDTOs.Full.class), HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update User")
    public ResponseEntity<UserDTOs.Full> updateUser(@PathVariable Long id,
            @Nonnull @Valid @RequestBody final UserData.Edit data) {
        log.info("Received update request for user : {}");
        return ResponseEntity.ok(mappingUtil.map(userService.edit(data, id), UserDTOs.Full.class));
    }

    @Transactional
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete User")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.userService.delete(id);
        return ResponseEntity.ok().build();
    }
}
