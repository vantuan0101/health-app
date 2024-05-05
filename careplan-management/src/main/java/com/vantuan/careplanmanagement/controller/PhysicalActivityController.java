package com.vantuan.careplanmanagement.controller;

import com.vantuan.careplanmanagement.criteria.PhysicalActivityCriteria;
import com.vantuan.careplanmanagement.model.data.PhysicalActivityData;
import com.vantuan.careplanmanagement.model.entity.PhysicalActivity;
import com.vantuan.careplanmanagement.read.PhysicalActivityDTOs;
import com.vantuan.careplanmanagement.service.PhysicalActivityService;
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
@Tag(name = "Patient physical activity")
@RestController
@RequestMapping("/careplan/physicalActivity")
@RequiredArgsConstructor
public class PhysicalActivityController extends BaseController<PhysicalActivity, Long, PhysicalActivityCriteria> {

    private final MappingUtil mappingUtil;
    private final PhysicalActivityService physicalActivityService;

    @Transactional
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create physical activity")
    public ResponseEntity<PhysicalActivityDTOs.Full> create(
            @Nonnull @Valid @RequestBody final PhysicalActivityData.Create data, HttpServletRequest request) {
        log.info("Received register physical activity request for user : {}", data.getPatientId());
        return new ResponseEntity<>(
                mappingUtil.map(physicalActivityService.create(data, request), PhysicalActivityDTOs.Full.class),
                HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Edit physical activity")
    public ResponseEntity<PhysicalActivityDTOs.Full> edit(
            @PathVariable final Long id,
            @Nonnull @Valid @RequestBody final PhysicalActivityData.Edit data) {
        log.info("Received edit physical activity request for user : {}", id);
        return ResponseEntity
                .ok(mappingUtil.map(physicalActivityService.edit(data, id), PhysicalActivityDTOs.Full.class));
    }

    @Transactional(readOnly = true)
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all physical activity information")
    public ResponseEntity<List<PhysicalActivityDTOs.Full>> getAll() {
        log.info("Received Get all medications information request for user : {}");
        return ResponseEntity
                .ok(mappingUtil.mapList(physicalActivityService.getAll(), PhysicalActivityDTOs.Full.class));
    }

    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get physical activity information")
    public ResponseEntity<PhysicalActivityDTOs.Full> get(
            @PathVariable final Long id) {
        log.info("Received get physical activity information request for user : {}", id);
        return ResponseEntity
                .ok(mappingUtil.map(physicalActivityService.get(id), PhysicalActivityDTOs.Full.class));
    }

    @Transactional
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete physical activity")
    public ResponseEntity<Void> delete(
            @PathVariable final Long id) {
        log.info("Received delete physical activity information request for user : {}", id);
        physicalActivityService.delete(id);
        return ResponseEntity.ok().build();
    }

}