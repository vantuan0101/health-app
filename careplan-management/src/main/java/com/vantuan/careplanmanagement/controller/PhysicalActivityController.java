package com.vantuan.careplanmanagement.controller;

import com.vantuan.careplanmanagement.criteria.PhysicalActivityCriteria;
import com.vantuan.careplanmanagement.model.entity.Patient;
import com.vantuan.careplanmanagement.model.entity.PhysicalActivity;
import com.vantuan.careplanmanagement.service.PhysicalActivityService;
import com.vantuan.common.exception.BadRequestException;
import com.vantuan.common.exception.EntityNotFoundException;
import com.vantuan.common.exception.ValidationException;
import com.vantuan.crud.controller.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@Tag(name = "Patient physical activity")
@RestController
@RequestMapping("/careplan")
@RequiredArgsConstructor
public class PhysicalActivityController extends BaseController<PhysicalActivity, PhysicalActivityCriteria> {

    private PhysicalActivityService physicalActivityService;

    @Autowired
    public void setPhysicalActivityService(PhysicalActivityService physicalActivityService) {
        this.physicalActivityService = physicalActivityService;
    }

    @Transactional
    @PostMapping("/physicalActivity")
    @ResponseStatus(CREATED)
    @Operation(summary = "Add patient physical activity")
    public ResponseEntity<PhysicalActivityCriteria> addPhysicalActivity(@Nonnull @PathVariable Long patientId,
            @Nonnull @RequestBody PhysicalActivityCriteria data, HttpServletRequest request)
            throws ValidationException {
        log.info("Received add patient physical activity request for user : {}");

        Patient patient = this.physicalActivityService.getPatient(request, patientId);
        data.setPatient(patient);
        PhysicalActivity newData = this.physicalActivityService.save(data);
        return ResponseEntity.ok(this.physicalActivityService.convertToDto(newData));
    }

    @Transactional
    @PutMapping("/physicalActivity")
    @ResponseStatus(OK)
    @Operation(summary = "Edit patient physical activity")
    public ResponseEntity<PhysicalActivityCriteria> editPhysicalActivity(@Nonnull @PathVariable final Long patientId,
            @Nonnull @Valid @RequestBody final PhysicalActivityCriteria data,
            HttpServletRequest request) throws EntityNotFoundException, ValidationException {
        log.info("Received edit patient physical activity request for user : {}");
        Patient patient = this.physicalActivityService.getPatient(request, patientId);
        PhysicalActivity physicalActivity = this.physicalActivityService.findById(data.getId());
        if (physicalActivity.getPatient().getId() != patient.getId())
            throw new BadRequestException("Edit physical activity id not match with patient");
        PhysicalActivity newData = updateData(physicalActivity, data);
        PhysicalActivity newPhysical = this.physicalActivityService.saveByModel(newData);
        return ResponseEntity.ok(this.physicalActivityService.convertToDto(newPhysical));
    }

    private PhysicalActivity updateData(final PhysicalActivity existing, final PhysicalActivityCriteria data) {
        return existing.toBuilder()
                .daysOfModerateActivity(data.getDaysOfModerateActivity())
                .minutesOfModerateActivity(data.getMinutesOfModerateActivity())
                .daysOfVigorousActivity(data.getDaysOfVigorousActivity())
                .minutesOfVigorousActivity(data.getMinutesOfVigorousActivity())
                .build();
    }

    @Transactional
    @DeleteMapping("/physicalActivity")
    @Operation(summary = "Remove physical activity from patient")
    public ResponseEntity<String> removeVital(@Nonnull @PathVariable final Long patientId,
            @Nonnull @Valid @RequestBody final PhysicalActivityCriteria data,
            HttpServletRequest request) throws EntityNotFoundException {
        log.info("Received remove physical activity from patient request for user : {}");
        Patient patient = this.physicalActivityService.getPatient(request, patientId);
        PhysicalActivity physicalActivity = this.physicalActivityService.findById(data.getId());
        if (physicalActivity.getPatient().getId() != patient.getId())
            throw new BadRequestException("Remove physical activity id not match with patient");

        Long physicalActivityId = physicalActivity.getId();
        this.physicalActivityService.deleteById(physicalActivityId);
        return ResponseEntity.ok("Delete Ok");
    }

}