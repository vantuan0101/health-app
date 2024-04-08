package com.vantuan.patientmanagement.controller;

import com.vantuan.crud.controller.BaseController;
import com.vantuan.patientmanagement.criteria.PatientCriteria;
import com.vantuan.patientmanagement.model.data.PatientData;
import com.vantuan.patientmanagement.model.entity.Patient;
import com.vantuan.patientmanagement.read.PatientDTOs;
import com.vantuan.patientmanagement.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nonnull;
import javax.validation.Valid;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;

@Slf4j
@Tag(name = "Patient")
@RestController
@Validated
@RequestMapping(path = "/patient")
@RequiredArgsConstructor
public class PatientController extends BaseController<Patient, Long , PatientCriteria> {

    private final PatientService patientService;
    @Transactional
    @PostMapping()
    @ResponseStatus(CREATED)
    @Operation(summary = "Register patient")
    public ResponseEntity<PatientDTOs.Full> register(@Nonnull @Valid @RequestBody final PatientData.Create data) {
        log.info("Received register patient request for user : {}");
        Patient patient = patientService.create(data);
        log.info("Patient:",patient);
        log.info("a :",map(patient, PatientDTOs.Full.class));
        return new ResponseEntity<>(map(patientService.create(data), PatientDTOs.Full.class),
                CREATED);
    }

//    @Transactional
//    @PutMapping()
//    @ResponseStatus(OK)
//    @Operation(summary = "Edit patient")
//    public ResponseEntity<PatientDTOs.Full> edit(@Nonnull @Valid @RequestBody final PatientData.Edit data) {
//        log.info("Received edit patient request for user : {}", getCurrentUsername());
//        return ResponseEntity.ok(map(patientService.edit(data, getCurrentUser()), PatientDTOs.Full.class));
//    }
}
