package com.vantuan.careplanmanagement.service;

import com.vantuan.careplanmanagement.common.exception.ResourceNotCreatedException;
import com.vantuan.careplanmanagement.common.exception.ResourceNotUpdatedException;
import com.vantuan.careplanmanagement.criteria.AllergyCriteria;
import com.vantuan.careplanmanagement.model.data.AllergyData;
import com.vantuan.careplanmanagement.model.entity.Allergy;
import com.vantuan.careplanmanagement.model.entity.Patient;
import com.vantuan.careplanmanagement.repository.AllergyDAO;
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
public class AllergyService extends BaseService<Allergy, Long, AllergyCriteria> {
    private final AllergyDAO allergyDAO;
    private final MappingUtil mappingUtil;
    private final WebClient.Builder webClientBuilder;

    @Transactional
    public Allergy create(final AllergyData.Create data, HttpServletRequest request) {
        log.info("Creating allergy with patient id : {}", data.getPatientId());
        final Patient patient = getPatient(request, data.getPatientId());
        data.setPatient(patient);
        return Try.of(() -> super.save(mappingUtil.map(data, Allergy.class)))
                .getOrElseThrow(ResourceNotCreatedException::new);
    }

    @Transactional
    public Allergy edit(final AllergyData.Edit data, final Long id) {
        final Allergy allergy = allergyDAO.get(id);
        log.info("Editing allergy with patient id : {}", id);
        return Try.of(() -> super.update(updateData(allergy, data)))
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

    private Allergy updateData(final Allergy allergy, final AllergyData.Edit data) {
        return allergy.toBuilder()
                .substance(data.getSubstance())
                .reaction(data.getReaction())
                .onset(data.getOnset())
                .severity(data.getSeverity())
                .medication(data.getMedication())
                .notes(data.getNotes())
                .build();
    }

}