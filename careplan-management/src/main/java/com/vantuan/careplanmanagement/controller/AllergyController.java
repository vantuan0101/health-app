package com.vantuan.careplanmanagement.controller;

import com.vantuan.careplanmanagement.criteria.AllergyCriteria;
import com.vantuan.careplanmanagement.model.data.AllergyData;
import com.vantuan.careplanmanagement.model.entity.Allergy;
import com.vantuan.careplanmanagement.read.AllergyDTOs;
import com.vantuan.careplanmanagement.service.AllergyService;
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
@Tag(name = "Patient allergies")
@RestController
@RequestMapping("/careplan/allergies")
@RequiredArgsConstructor
public class AllergyController extends BaseController<Allergy, Long, AllergyCriteria> {
    private final MappingUtil mappingUtil;
    private final AllergyService allergyService;

    @Transactional
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create allergies")
    public ResponseEntity<AllergyDTOs.Full> create(@Nonnull @Valid @RequestBody final AllergyData.Create data,
            HttpServletRequest request) {
        log.info("Received register allergies request for user : {}", data.getPatientId());
        return new ResponseEntity<>(mappingUtil.map(allergyService.create(data, request), AllergyDTOs.Full.class),
                HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Edit allergies")
    public ResponseEntity<AllergyDTOs.Full> edit(
            @PathVariable final Long id,
            @Nonnull @Valid @RequestBody final AllergyData.Edit data) {
        log.info("Received register allergies request for user : {}", id);
        return ResponseEntity.ok(mappingUtil.map(allergyService.edit(data, id), AllergyDTOs.Full.class));
    }

    @Transactional(readOnly = true)
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all allergies information")
    public ResponseEntity<List<AllergyDTOs.Full>> getAll() {
        log.info("Received Get all allergies information request for user : {}");
        return ResponseEntity
                .ok(mappingUtil.mapList(allergyService.getAll(), AllergyDTOs.Full.class));
    }

    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get allergies information")
    public ResponseEntity<AllergyDTOs.Full> get(
            @PathVariable final Long id) {
        log.info("Received get allergies information request for user : {}", id);
        return ResponseEntity
                .ok(mappingUtil.map(allergyService.get(id), AllergyDTOs.Full.class));
    }

    @Transactional
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete allergies")
    public ResponseEntity<Void> delete(
            @PathVariable final Long id) {
        log.info("Received delete allergies information request for user : {}", id);
        allergyService.delete(id);
        return ResponseEntity.ok().build();
    }
}
