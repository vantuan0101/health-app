package com.vantuan.careplanmanagement.controller;

import com.vantuan.careplanmanagement.criteria.MedicationCriteria;
import com.vantuan.careplanmanagement.model.data.MedicationData;
import com.vantuan.careplanmanagement.model.entity.Medication;
import com.vantuan.careplanmanagement.read.MedicationDTOs;
import com.vantuan.careplanmanagement.service.MedicationService;
import com.vantuan.framework.common.mapper.MappingUtil;
import com.vantuan.framework.crud.controller.BaseController;
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
@Tag(name = "Patient medications")
@RestController
@RequestMapping("/careplan/medications")
@RequiredArgsConstructor
public class MedicationController extends BaseController<Medication, Long, MedicationCriteria> {

    private final MappingUtil mappingUtil;
    private final MedicationService medicationService;

    @Transactional
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create medications")
    public ResponseEntity<MedicationDTOs.Full> create(@Nonnull @Valid @RequestBody final MedicationData.Create data,
            HttpServletRequest request) {
        log.info("Received register medications request for user : {}", data.getPatientId());
        return new ResponseEntity<>(mappingUtil.map(medicationService.create(data, request), MedicationDTOs.Full.class),
                HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Edit medications")
    public ResponseEntity<MedicationDTOs.Full> edit(
            @PathVariable final Long id,
            @Nonnull @Valid @RequestBody final MedicationData.Edit data) {
        log.info("Received register medications request for user : {}", id);
        return ResponseEntity.ok(mappingUtil.map(medicationService.edit(data, id), MedicationDTOs.Full.class));
    }

    @Transactional(readOnly = true)
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all medications information")
    public ResponseEntity<List<MedicationDTOs.Full>> getAll() {
        log.info("Received Get all medications information request for user : {}");
        return ResponseEntity
                .ok(mappingUtil.mapList(medicationService.getAll(), MedicationDTOs.Full.class));
    }

    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get medications information")
    public ResponseEntity<MedicationDTOs.Full> get(
            @PathVariable final Long id) {
        log.info("Received get medications information request for user : {}", id);
        return ResponseEntity
                .ok(mappingUtil.map(medicationService.get(id), MedicationDTOs.Full.class));
    }

    @Transactional
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete medications")
    public ResponseEntity<Void> delete(
            @PathVariable final Long id) {
        log.info("Received delete medications information request for user : {}", id);
        medicationService.delete(id);
        return ResponseEntity.ok().build();
    }

}