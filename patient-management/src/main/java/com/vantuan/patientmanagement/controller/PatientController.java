package com.vantuan.patientmanagement.controller;

import com.vantuan.common.exception.EntityNotFoundException;
import com.vantuan.common.exception.ValidationException;
import com.vantuan.crud.controller.BaseController;
import com.vantuan.patientmanagement.common.user.model.User;
import com.vantuan.patientmanagement.criteria.PatientCriteria;
import com.vantuan.patientmanagement.model.entity.Patient;
import com.vantuan.patientmanagement.service.PatientService;
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
@Tag(name = "Patient")
@RestController
@RequestMapping(path = "/patient")
@RequiredArgsConstructor
public class PatientController extends BaseController<Patient, PatientCriteria> {

    private PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @Transactional
    @GetMapping("/{id}")
    public ResponseEntity<PatientCriteria> findById(@PathVariable Long id) throws EntityNotFoundException {
        Patient patient = this.patientService.findById(id);
        PatientCriteria patientCriteria = this.patientService.convertToDto(patient);
        return ResponseEntity.ok(patientCriteria);
    }

    @Transactional
    @GetMapping("filters")
    public ResponseEntity<Collection<String>> getFiltersController() {
        return ResponseEntity.ok(getFilters());
    }

    @Transactional
    @GetMapping("filters/{filter_name}")
    public ResponseEntity<Collection<PatientCriteria>> filter(@PathVariable(value = "filter_name") String filterName,
            @RequestParam Map<String, String> dataQuery) throws ValidationException {
        return ResponseEntity.ok(this.filters.get(filterName).filterTemplate(dataQuery));
    }

    @Transactional
    @PostMapping()
    public ResponseEntity<PatientCriteria> save(@RequestBody PatientCriteria dto, HttpServletRequest request)
            throws ValidationException {
        String email = dto.getEmail();
        User user = this.patientService.getUserEmail(request, email);
        if (user != null) {
            dto.setUser(user);
        }
        Patient patient = this.patientService.save(dto);
        return ResponseEntity.ok(this.patientService.convertToDto(patient));
    }

    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity<PatientCriteria> update(@PathVariable Long id, @RequestBody PatientCriteria dto)
            throws ValidationException {
        Patient patient = this.patientService.update(id, dto);
        return ResponseEntity.ok(this.patientService.convertToDto(patient));
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<PatientCriteria> updateAll(@PathVariable Long id, @RequestBody PatientCriteria dto)
            throws ValidationException {
        Patient patient = this.patientService.updateAll(id, dto);
        return ResponseEntity.ok(this.patientService.convertToDto(patient));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws EntityNotFoundException {
        this.patientService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
