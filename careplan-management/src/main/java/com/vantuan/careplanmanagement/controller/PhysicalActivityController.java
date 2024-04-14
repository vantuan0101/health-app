package com.vantuan.careplanmanagement.controller;

import com.vantuan.careplanmanagement.criteria.PhysicalActivityCriteria;
import com.vantuan.careplanmanagement.model.entity.Patient;
import com.vantuan.careplanmanagement.service.PhysicalActivityService;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nonnull;
import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@Tag(name = "Patient physical activity")
@RestController
@Validated
@RequestMapping("/careplan")
@RequiredArgsConstructor
public class PhysicalActivityController extends BaseController<PhysicalActivity, PhysicalActivityCriteria> {

    private PhysicalActivityService physicalActivityService;

    @Autowired
    public void setPhysicalActivityServicee(PhysicalActivityService physicalActivityService) {
        this.physicalActivityService = physicalActivityService;
    }

    @Transactional
    @PostMapping("/physicalActivity")
    @ResponseStatus(CREATED)
    @Operation(summary = "Add patient physical activity")
    public ResponseEntity<PhysicalActivityCriteria> addPhysicalActivity(@Nonnull @PathVariable Long patientId,
                                                                        @Nonnull @RequestBody PhysicalActivityCriteria data, HttpServletRequest request) throws ValidationException {
        log.info("Received add vital to patient request for user : {}");

        Patient patient = this.physicalActivityService.getPatient(request, patientId);
        data.setPatient(patient);
        PhysicalActivity newData = this.physicalActivityService.save(data);
        return ResponseEntity.ok(this.physicalActivityService.convertToDto(newData));
    }

    @Transactional
    @PutMapping("/vitals/{patientId}")
    @ResponseStatus(OK)
    @Operation(summary = "Edit patient vital")
    public ResponseEntity<PhysicalActivityCriteria> editVital(@Nonnull @PathVariable final Long patientId,
                                                              @Nonnull @Valid @RequestBody final PhysicalActivityCriteria data,
                                                              HttpServletRequest request) throws EntityNotFoundException, ValidationException {
        log.info("Received edit patient vital request for user : {}");
        Patient patient = this.physicalActivityService.getPatient(request, patientId);
        PhysicalActivity physicalActivity = this.physicalActivityService.findById(data.getId());
        if (physicalActivity.getPatient().getId() != patient.getId())
            throw new BadRequestException("Edit vital id not match with patient");
        PhysicalActivity newData = this.physicalActivityService.update(physicalActivity.getPatient().getId(), data);
        return ResponseEntity.ok(this.physicalActivityService.convertToDto(newData));
    }


    @Transactional
    @DeleteMapping("/vitals/{patientId}")
    @Operation(summary = "Remove vital from patient")
    public ResponseEntity<String> removeVital(@Nonnull @PathVariable final Long patientId,
                                              @Nonnull @Valid @RequestBody final PhysicalActivityCriteria data,
                                              HttpServletRequest request) throws EntityNotFoundException {
        log.info("Received remove vital from patient request for user : {}");
        Patient patient = this.physicalActivityService.getPatient(request, patientId);
        PhysicalActivity physicalActivity = this.physicalActivityService.findById(data.getId());
        if (physicalActivity.getPatient().getId() != patient.getId())
            throw new BadRequestException("Remove vital id not match with patient");

        Long physicalActivityId = physicalActivity.getId();
        this.physicalActivityService.deleteById(physicalActivityId);
        return ResponseEntity.ok("Delete Ok");
    }

}