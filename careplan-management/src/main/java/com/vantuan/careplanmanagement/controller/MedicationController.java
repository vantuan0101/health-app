package com.vantuan.careplanmanagement.controller;

import com.vantuan.careplanmanagement.criteria.MedicationCriteria;
import com.vantuan.careplanmanagement.model.entity.Medication;
import com.vantuan.careplanmanagement.model.entity.Patient;
import com.vantuan.careplanmanagement.service.MedicationService;
import com.vantuan.common.exception.BadRequestException;
import com.vantuan.common.exception.EntityNotFoundException;
import com.vantuan.common.exception.ValidationException;
import com.vantuan.crud.controller.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nonnull;
import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@Tag(name = "Patient medications")
@RestController
@RequestMapping("/careplan")
@RequiredArgsConstructor
public class MedicationController extends BaseController<Medication, MedicationCriteria> {

    private MedicationService medicationService;

    @Autowired
    public void setMedicationService(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @Transactional
    @PostMapping("/medications")
    @ResponseStatus(CREATED)
    @Operation(summary = "Add medications to patient")
    public ResponseEntity<MedicationCriteria> addMedications(@Nonnull @PathVariable Long patientId,
            @Nonnull @RequestBody MedicationCriteria data, HttpServletRequest request) throws ValidationException {
        log.info("Received add medications to patient request for user : {}");

        Patient patient = this.medicationService.getPatient(request, patientId);
        data.setPatient(patient);
        Medication newData = this.medicationService.save(data);
        return ResponseEntity.ok(this.medicationService.convertToDto(newData));
    }

    @Transactional
    @PutMapping("/medications")
    @ResponseStatus(OK)
    @Operation(summary = "Edit patient medication")
    public ResponseEntity<MedicationCriteria> editMedication(@Nonnull @PathVariable final Long patientId,
            @Nonnull @Valid @RequestBody final MedicationCriteria data,
            HttpServletRequest request) throws EntityNotFoundException, ValidationException {
        log.info("Received edit patient medication request for user : {}");
        Patient patient = this.medicationService.getPatient(request, patientId);
        Medication medication = this.medicationService.findById(data.getId());
        if (medication.getPatient().getId() != patient.getId())
            throw new BadRequestException("Edit patient medication id not match with patient");
        Medication newData = this.medicationService.update(medication.getPatient().getId(), data);
        return ResponseEntity.ok(this.medicationService.convertToDto(newData));
    }

    @Transactional
    @DeleteMapping("/medications")
    public ResponseEntity<String> removeVital(@Nonnull @PathVariable final Long patientId,
            @Nonnull @Valid @RequestBody final MedicationCriteria data,
            HttpServletRequest request) throws EntityNotFoundException {
        log.info("Received remove patient medication from patient request for user : {}");
        Patient patient = this.medicationService.getPatient(request, patientId);
        Medication medication = this.medicationService.findById(data.getId());
        if (medication.getPatient().getId() != patient.getId())
            throw new BadRequestException("Remove patient medication id not match with patient");

        Long medicationId = medication.getId();
        this.medicationService.deleteById(medicationId);
        return ResponseEntity.ok("Delete Ok");
    }

}