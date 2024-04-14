package com.vantuan.careplanmanagement.controller;

import com.vantuan.careplanmanagement.criteria.AllergyCriteria;
import com.vantuan.careplanmanagement.model.entity.Allergy;
import com.vantuan.careplanmanagement.model.entity.Patient;
import com.vantuan.careplanmanagement.service.AllergyService;
import com.vantuan.common.exception.BadRequestException;
import com.vantuan.common.exception.EntityNotFoundException;
import com.vantuan.common.exception.ValidationException;
import com.vantuan.crud.controller.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "Patient allergies")
@RestController
@RequestMapping("/careplan")
@RequiredArgsConstructor
public class AllergyController extends BaseController<Allergy, AllergyCriteria> {
    private AllergyService allergyService;

    @Autowired
    public void setAllergyService(AllergyService allergyService) {
        this.allergyService = allergyService;
    }

    @Transactional
    @PostMapping("/allergies")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add allergies to patient")
    public ResponseEntity<AllergyCriteria> addAllergies(@Nonnull @PathVariable Long patientId,
            @Nonnull @RequestBody AllergyCriteria data, HttpServletRequest request) throws ValidationException {
        log.info("Received add allergies to patient request for user : {}");
        Patient patient = this.allergyService.getPatient(request, patientId);
        data.setPatient(patient);
        Allergy newData = this.allergyService.save(data);
        return ResponseEntity.ok(this.allergyService.convertToDto(newData));
    }

    @Transactional
    @PutMapping("/allergies")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Edit patient allergy")
    public ResponseEntity<AllergyCriteria> editAllergy(@Nonnull @PathVariable Long patientId,
            @Nonnull @RequestBody AllergyCriteria data, HttpServletRequest request)
            throws ValidationException, EntityNotFoundException {
        log.info("Received edit patient allergy request for user : {}");
        Patient patient = this.allergyService.getPatient(request, patientId);
        Allergy allergy = this.allergyService.findById(data.getId());
        if (allergy.getPatient().getId() != patient.getId())
            throw new BadRequestException("Edit patient allergy id not match with patient");
        Allergy newData = this.allergyService.update(allergy.getPatient().getId(), data);
        return ResponseEntity.ok(this.allergyService.convertToDto(newData));
    }

    @Transactional
    @DeleteMapping("/allergies")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Remove allergy from patient")
    public ResponseEntity<String> removeAllergy(@Nonnull @PathVariable Long patientId,
            @Nonnull @RequestBody AllergyCriteria data, HttpServletRequest request) throws EntityNotFoundException {
        log.info("Received remove allergy from patient request for user : {}");
        Patient patient = this.allergyService.getPatient(request, patientId);
        Allergy allergy = this.allergyService.findById(data.getId());
        if (allergy.getPatient().getId() != patient.getId())
            throw new BadRequestException("Remove allergy id not match with patient");

        Long id = allergy.getId();
        this.allergyService.deleteById(id);
        return ResponseEntity.ok("Delete Ok");
    }
}
