package com.vantuan.clinicmanagement.controller;

import com.vantuan.clinicmanagement.common.user.model.User;
import com.vantuan.clinicmanagement.criteria.ClinicianCriteria;
import com.vantuan.clinicmanagement.model.entity.Clinician;
import com.vantuan.clinicmanagement.service.ClinicService;
import com.vantuan.common.exception.EntityNotFoundException;
import com.vantuan.common.exception.ValidationException;
import com.vantuan.crud.controller.BaseController;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@Slf4j
@Tag(name = "Clinician")
@RestController
@RequestMapping(path = "/clinician")
@RequiredArgsConstructor
public class ClinicController extends BaseController<Clinician, ClinicianCriteria> {

    private ClinicService clinicService;

    @Autowired
    public ClinicController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @Transactional
    @GetMapping("/{id}")
    public ResponseEntity<ClinicianCriteria> findById(@PathVariable Long id) throws EntityNotFoundException {
        Clinician clinician = this.clinicService.findById(id);
        this.clinicService.findById(id);
        ClinicianCriteria patientCriteria = this.clinicService.convertToDto(clinician);
        return ResponseEntity.ok(patientCriteria);
    }

    @Transactional
    @GetMapping("filters")
    public ResponseEntity<Collection<String>> getFiltersController() {
        return ResponseEntity.ok(getFilters());
    }

    @Transactional
    @GetMapping("filters/{filter_name}")
    public ResponseEntity<Collection<ClinicianCriteria>> filter(@PathVariable(value = "filter_name") String filterName,
            @RequestParam Map<String, String> dataQuery) throws ValidationException {
        return ResponseEntity.ok(this.filters.get(filterName).filterTemplate(dataQuery));
    }

    @Transactional
    @PostMapping()
    public ResponseEntity<ClinicianCriteria> save(@RequestBody ClinicianCriteria dto, HttpServletRequest request)
            throws ValidationException {
        String email = dto.getEmail();
        User user = this.clinicService.getUserEmail(request, email);
        if (user != null) {
            dto.setUser(user);
        }
        Clinician clinician = this.clinicService.save(dto);
        return ResponseEntity.ok(this.clinicService.convertToDto(clinician));
    }

    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity<ClinicianCriteria> update(@PathVariable Long id, @RequestBody ClinicianCriteria dto)
            throws ValidationException {
        Clinician clinician = this.clinicService.update(id, dto);
        return ResponseEntity.ok(this.clinicService.convertToDto(clinician));
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<ClinicianCriteria> updateAll(@PathVariable Long id, @RequestBody ClinicianCriteria dto)
            throws ValidationException {
        Clinician clinician = this.clinicService.updateAll(id, dto);
        return ResponseEntity.ok(this.clinicService.convertToDto(clinician));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws EntityNotFoundException {
        this.clinicService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}