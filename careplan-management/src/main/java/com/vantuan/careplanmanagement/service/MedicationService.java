package com.vantuan.careplanmanagement.service;

import com.vantuan.careplanmanagement.common.exception.ResourceNotCreatedException;
import com.vantuan.careplanmanagement.common.exception.ResourceNotUpdatedException;
import com.vantuan.careplanmanagement.criteria.MedicationCriteria;
import com.vantuan.careplanmanagement.model.data.MedicationData;
import com.vantuan.careplanmanagement.model.entity.Medication;
import com.vantuan.careplanmanagement.model.entity.Patient;
import com.vantuan.careplanmanagement.repository.MedicationDAO;
import com.vantuan.framework.common.mapper.MappingUtil;
import com.vantuan.framework.crud.service.BaseService;
import io.vavr.control.Try;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.Nonnull;

@Slf4j
@Service
@RequiredArgsConstructor
public class MedicationService extends BaseService<Medication, Long, MedicationCriteria> {
    private final MappingUtil mappingUtil;
    private final WebClient.Builder webClientBuilder;
    private final MedicationDAO medicationDAO;

    @Transactional
    public Medication create(final MedicationData.Create data, HttpServletRequest request) {
        log.info("Creating medication with patient id : {}", data.getPatientId());
        final Patient patient = getPatient(request, data.getPatientId());
        data.setPatient(patient);
        return Try.of(() -> super.save(mappingUtil.map(data, Medication.class)))
                .getOrElseThrow(ResourceNotCreatedException::new);
    }

    @Transactional
    public Medication edit(final MedicationData.Edit data, final Long id) {
        final Medication medication = medicationDAO.get(id);
        log.info("Editing medication with patient id : {}", id);
        return Try.of(() -> super.update(updateData(medication, data)))
                .getOrElseThrow(ResourceNotUpdatedException::new);
    }

    public Patient getPatient(HttpServletRequest request, @Nonnull String patientId) {
        String headerAuth = request.getHeader("Authorization");
        Patient patient = this.webClientBuilder.build().get()
                .uri("http://patient-management/api/patient/", uriBuilder -> uriBuilder.path("/{id}").build(patientId))
                .header("Authorization", headerAuth)
                .retrieve()
                .bodyToMono(Patient.class)
                .block();
        return patient;
    }

    private Medication updateData(final Medication medication, final MedicationData.Edit data) {
        return medication.toBuilder()
                .medicineName(data.getMedicineName())
                .quantity(data.getQuantity())
                .unit(data.getUnit())
                .startDate(data.getStartDate())
                .estimatedEndDate(data.getEstimatedEndDate())
                .doseUnit(data.getDoseUnit())
                .frequency(data.getFrequency())
                .notes(data.getNotes())
                .build();
    }
}