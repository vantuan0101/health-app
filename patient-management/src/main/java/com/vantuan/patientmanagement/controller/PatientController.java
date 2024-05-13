package com.vantuan.patientmanagement.controller;

import com.vantuan.framework.common.mapper.MappingUtil;
import com.vantuan.framework.crud.controller.BaseController;
import com.vantuan.patientmanagement.criteria.PatientCriteria;
import com.vantuan.patientmanagement.model.data.PatientData;
import com.vantuan.patientmanagement.model.entity.Patient;
import com.vantuan.patientmanagement.read.PatientDTOs;
import com.vantuan.patientmanagement.service.PatientService;
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
@Tag(name = "Patient")
@RestController
@RequestMapping(path = "/patient")
@RequiredArgsConstructor
public class PatientController extends BaseController<Patient, Long, PatientCriteria> {
    private final MappingUtil mappingUtil;
    private final PatientService patientService;

    @Transactional
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Register patient")
    public ResponseEntity<PatientDTOs.Full> register(@Nonnull @Valid @RequestBody final PatientData.Create data,
            HttpServletRequest request) {
        log.info("Received register patient request for user : {}", data.getClinicianId());
        return new ResponseEntity<>(mappingUtil.map(patientService.create(data, request), PatientDTOs.Full.class),
                HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Edit patient")
    public ResponseEntity<PatientDTOs.Full> edit(
            @PathVariable final Long id,
            @Nonnull @Valid @RequestBody final PatientData.Edit data) {
        log.info("Received register patient request for user : {}", id);
        return ResponseEntity.ok(mappingUtil.map(patientService.edit(data, id), PatientDTOs.Full.class));
    }

    @Transactional(readOnly = true)
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all patient information")
    public ResponseEntity<List<PatientDTOs.Full>> getAll() {
        log.info("Received Get all clinician information request for user : {}");
        return ResponseEntity
                .ok(mappingUtil.mapList(patientService.getAll(), PatientDTOs.Full.class));
    }

    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get patient information")
    public ResponseEntity<PatientDTOs.Full> get(
            @PathVariable final Long id) {
        log.info("Received get patient information request for user : {}", id);
        return ResponseEntity
                .ok(mappingUtil.map(patientService.get(id), PatientDTOs.Full.class));
    }

    @Transactional
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete patient")
    public ResponseEntity<Void> delete(
            @PathVariable final Long id) {
        log.info("Received delete patient information request for user : {}", id);
        patientService.delete(id);
        return ResponseEntity.ok().build();
    }
}
