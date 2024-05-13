package com.vantuan.careplanmanagement.controller;

import com.vantuan.careplanmanagement.criteria.VitalCriteria;
import com.vantuan.careplanmanagement.model.data.VitalData;
import com.vantuan.careplanmanagement.model.entity.Vital;
import com.vantuan.careplanmanagement.read.VitalDTOs;
import com.vantuan.careplanmanagement.service.VitalService;
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
@Tag(name = "Patient vitals")
@RestController
@RequestMapping("/careplan/vitals")
@RequiredArgsConstructor
public class VitalController extends BaseController<Vital, Long, VitalCriteria> {

    private final MappingUtil mappingUtil;
    private final VitalService vitalService;

    @Transactional
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create vitals")
    public ResponseEntity<VitalDTOs.Full> create(@Nonnull @Valid @RequestBody final VitalData.Create data,
            HttpServletRequest request) {
        log.info("Received register vitals request for user : {}", data.getPatientId());
        return new ResponseEntity<>(mappingUtil.map(vitalService.create(data, request), VitalDTOs.Full.class),
                HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Edit vitals")
    public ResponseEntity<VitalDTOs.Full> edit(
            @PathVariable final Long id,
            @Nonnull @Valid @RequestBody final VitalData.Edit data) {
        log.info("Received edit vitals request for user : {}", id);
        return ResponseEntity.ok(mappingUtil.map(vitalService.edit(data, id), VitalDTOs.Full.class));
    }

    @Transactional(readOnly = true)
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all vitals information")
    public ResponseEntity<List<VitalDTOs.Full>> getAll() {
        log.info("Received Get all vitals information request for user : {}");
        return ResponseEntity
                .ok(mappingUtil.mapList(vitalService.getAll(), VitalDTOs.Full.class));
    }

    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get vitals information")
    public ResponseEntity<VitalDTOs.Full> get(
            @PathVariable final Long id) {
        log.info("Received get vitals information request for user : {}", id);
        return ResponseEntity
                .ok(mappingUtil.map(vitalService.get(id), VitalDTOs.Full.class));
    }

    @Transactional
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete vitals")
    public ResponseEntity<Void> delete(
            @PathVariable final Long id) {
        log.info("Received delete vitals information request for user : {}", id);
        vitalService.delete(id);
        return ResponseEntity.ok().build();
    }

}
