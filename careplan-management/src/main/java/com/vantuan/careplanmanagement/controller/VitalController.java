package com.vantuan.careplanmanagement.controller;

import com.vantuan.careplanmanagement.criteria.VitalCriteria;
import com.vantuan.careplanmanagement.model.entity.Patient;
import com.vantuan.careplanmanagement.model.entity.Vital;
import com.vantuan.careplanmanagement.service.VitalService;
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
@Tag(name = "Patient vitals")
@RestController
@RequestMapping("/careplan")
@RequiredArgsConstructor
public class VitalController extends BaseController<Vital, VitalCriteria> {

    private VitalService vitalService;

    @Autowired
    public void setVitalService(VitalService vitalService) {
        this.vitalService = vitalService;
    }

    @Transactional
    @PostMapping("/vitals/{patientId}")
    @ResponseStatus(CREATED)
    @Operation(summary = "Add vital to patient")
    public ResponseEntity<VitalCriteria> addVital(@Nonnull @PathVariable Long patientId,
            @Nonnull @RequestBody VitalCriteria data, HttpServletRequest request) throws ValidationException {
        log.info("Received add vital to patient request for user : {}");

        Patient patient = this.vitalService.getPatient(request, patientId);
        data.setPatient(patient);
        Vital newData = this.vitalService.save(data);
        return ResponseEntity.ok(this.vitalService.convertToDto(newData));
    }

    @Transactional
    @PutMapping("/vitals/{patientId}")
    @ResponseStatus(OK)
    @Operation(summary = "Edit patient vital")
    public ResponseEntity<VitalCriteria> editVital(@Nonnull @PathVariable final Long patientId,
            @Nonnull @Valid @RequestBody final VitalCriteria data,
            HttpServletRequest request) throws EntityNotFoundException, ValidationException {
        log.info("Received edit patient vital request for user : {}");
        Patient patient = this.vitalService.getPatient(request, patientId);
        Vital vital = this.vitalService.findById(data.getId());
        if (vital.getPatient().getId() != patient.getId())
            throw new BadRequestException("Edit vital id not match with patient");
        Vital newVital = this.vitalService.update(vital.getPatient().getId(), data);
        return ResponseEntity.ok(this.vitalService.convertToDto(newVital));
    }

    @Transactional
    @DeleteMapping("/vitals/{patientId}")
    @Operation(summary = "Remove vital from patient")
    public ResponseEntity<String> removeVital(@Nonnull @PathVariable final Long patientId,
            @Nonnull @Valid @RequestBody final VitalCriteria data,
            HttpServletRequest request) throws EntityNotFoundException {
        log.info("Received remove vital from patient request for user : {}");
        Patient patient = this.vitalService.getPatient(request, patientId);
        Vital vital = this.vitalService.findById(data.getId());
        if (vital.getPatient().getId() != patient.getId())
            throw new BadRequestException("Remove vital id not match with patient");

        Long vitalId = vital.getId();
        this.vitalService.deleteById(vitalId);
        return ResponseEntity.ok("Delete Ok");
    }

}
