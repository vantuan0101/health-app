package com.vantuan.clinicmanagement.controller;

import com.vantuan.clinicmanagement.criteria.ClinicianCriteria;
import com.vantuan.clinicmanagement.model.data.ClinicianData;
import com.vantuan.clinicmanagement.model.entity.Clinician;
import com.vantuan.clinicmanagement.read.ClinicianDTOs;
import com.vantuan.clinicmanagement.service.ClinicianService;
import com.vantuan.common.mapper.MappingUtil;
import com.vantuan.crud.controller.BaseController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Tag(name = "Clinician")
@RestController
@RequestMapping(path = "/clinician")
@RequiredArgsConstructor
public class ClinicController extends BaseController<Clinician, Long, ClinicianCriteria> {

    private final MappingUtil mappingUtil;
    private final ClinicianService clinicianService;

    @Transactional
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Register clinician")
    public ResponseEntity<ClinicianDTOs.Full> register(@Nonnull @Valid @RequestBody final ClinicianData.Create data,
            HttpServletRequest request) {
        log.info("Received register clinician request for user : {}", data.getUserId());
        return new ResponseEntity<>(mappingUtil.map(clinicianService.create(data, request), ClinicianDTOs.Full.class),
                HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Edit clinician")
    public ResponseEntity<ClinicianDTOs.Full> edit(
            @PathVariable final Long id,
            @Nonnull @Valid @RequestBody final ClinicianData.Edit data) {
        log.info("Received register clinician request for user : {}", id);
        return ResponseEntity.ok(mappingUtil.map(clinicianService.edit(data, id), ClinicianDTOs.Full.class));
    }

    @Transactional(readOnly = true)
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all clinician information")
    public ResponseEntity<List<ClinicianDTOs.Full>> getAll() {
        log.info("Received Get all clinician information request for user : {}");
        return ResponseEntity
                .ok(mappingUtil.mapList(clinicianService.getAll(), ClinicianDTOs.Full.class));
    }

    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get clinician information")
    public ResponseEntity<ClinicianDTOs.Full> get(
            @PathVariable final Long id) {
        log.info("Received get clinician information request for user : {}", id);
        return ResponseEntity
                .ok(mappingUtil.map(clinicianService.get(id), ClinicianDTOs.Full.class));
    }

    @Transactional
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete clinician")
    public ResponseEntity<Void> delete(
            @PathVariable final Long id) {
        log.info("Received delete clinician information request for user : {}", id);
        clinicianService.delete(id);
        return ResponseEntity.ok().build();
    }
}