package com.vantuan.careplanmanagement.service;

import com.vantuan.careplanmanagement.common.exception.ResourceNotCreatedException;
import com.vantuan.careplanmanagement.common.exception.ResourceNotUpdatedException;
import com.vantuan.careplanmanagement.criteria.PhysicalActivityCriteria;
import com.vantuan.careplanmanagement.model.data.PhysicalActivityData;
import com.vantuan.careplanmanagement.model.entity.Patient;
import com.vantuan.careplanmanagement.model.entity.PhysicalActivity;
import com.vantuan.careplanmanagement.repository.PhysicalActivityDAO;
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
public class PhysicalActivityService extends BaseService<PhysicalActivity, Long, PhysicalActivityCriteria> {
    private final MappingUtil mappingUtil;
    private final WebClient.Builder webClientBuilder;
    private final PhysicalActivityDAO physicalActivityDAO;

    @Transactional
    public PhysicalActivity create(final PhysicalActivityData.Create data, HttpServletRequest request) {
        log.info("Creating vital with patient id : {}", data.getPatientId());
        final Patient patient = getPatient(request, data.getPatientId());
        data.setPatient(patient);
        return Try.of(() -> super.save(mappingUtil.map(data, PhysicalActivity.class)))
                .getOrElseThrow(ResourceNotCreatedException::new);
    }

    @Transactional
    public PhysicalActivity edit(final PhysicalActivityData.Edit data, final Long id) {
        final PhysicalActivity vital = physicalActivityDAO.get(id);
        log.info("Editing vital with patient id : {}", id);
        return Try.of(() -> super.update(updateData(vital, data)))
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

    private PhysicalActivity updateData(final PhysicalActivity existing, final PhysicalActivityData.Edit data) {
        return existing.toBuilder()
                .daysOfModerateActivity(data.getDaysOfModerateActivity())
                .minutesOfModerateActivity(data.getMinutesOfModerateActivity())
                .daysOfVigorousActivity(data.getDaysOfVigorousActivity())
                .minutesOfVigorousActivity(data.getMinutesOfVigorousActivity())
                .build();
    }
}