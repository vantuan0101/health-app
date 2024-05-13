package com.vantuan.careplanmanagement.service;

import com.vantuan.careplanmanagement.common.exception.ResourceNotCreatedException;
import com.vantuan.careplanmanagement.common.exception.ResourceNotUpdatedException;
import com.vantuan.careplanmanagement.criteria.VitalCriteria;
import com.vantuan.careplanmanagement.model.data.VitalData;
import com.vantuan.careplanmanagement.model.entity.Patient;
import com.vantuan.careplanmanagement.model.entity.Vital;
import com.vantuan.careplanmanagement.repository.VitalDAO;
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
public class VitalService extends BaseService<Vital, Long, VitalCriteria> {
    private final MappingUtil mappingUtil;
    private final WebClient.Builder webClientBuilder;
    private final VitalDAO vitalDAO;

    @Transactional
    public Vital create(final VitalData.Create data, HttpServletRequest request) {
        log.info("Creating vital with patient id : {}", data.getPatientId());
        final Patient patient = getPatient(request, data.getPatientId());
        data.setPatient(patient);
        return Try.of(() -> super.save(mappingUtil.map(data, Vital.class)))
                .getOrElseThrow(ResourceNotCreatedException::new);
    }

    @Transactional
    public Vital edit(final VitalData.Edit data, final Long id) {
        final Vital vital = vitalDAO.get(id);
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

    private Vital updateData(final Vital vital, final VitalData.Edit data) {
        return vital.toBuilder()
                .systolicBloodPressure(data.getSystolicBloodPressure())
                .diastolicBloodPressure(data.getDiastolicBloodPressure())
                .heartRate(data.getHeartRate())
                .oxygenSaturation(data.getOxygenSaturation())
                .temperature(data.getTemperature())
                .build();
    }

}