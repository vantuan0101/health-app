package com.vantuan.patientmanagement.service;

import com.vantuan.common.mapper.MappingUtil;
import com.vantuan.crud.service.BaseService;
import com.vantuan.patientmanagement.common.exceptions.ResourceNotCreatedException;
import com.vantuan.patientmanagement.common.exceptions.ResourceNotUpdatedException;
import com.vantuan.patientmanagement.criteria.PatientCriteria;
import com.vantuan.patientmanagement.model.data.PatientData;
import com.vantuan.patientmanagement.model.entity.Clinician;
import com.vantuan.patientmanagement.model.entity.Patient;
import com.vantuan.patientmanagement.repository.PatientDAO;
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
public class PatientService extends BaseService<Patient, Long, PatientCriteria> {
    private final MappingUtil mappingUtil;
    private final PatientDAO patientDAO;
    private final WebClient.Builder webClientBuilder;

    @Transactional
    public Patient create(final PatientData.Create data, HttpServletRequest request) {
        log.info("Creating patient with clinician id : {}", data.getClinicianId());
        final Clinician clinician = getClinician(request, data.getClinicianId());
        data.setClinician(clinician);
        return Try.of(() -> super.save(mappingUtil.map(data, Patient.class)))
                .getOrElseThrow(ResourceNotCreatedException::new);
    }

    @Transactional
    public Patient edit(final PatientData.Edit data, final Long id) {
        final Patient clinician = patientDAO.get(id);
        log.info("Editing patient with clinician id : {}", id);
        return Try.of(() -> super.update(updateData(clinician, data)))
                .getOrElseThrow(ResourceNotUpdatedException::new);
    }

    public Clinician getClinician(HttpServletRequest request, @Nonnull String id) {
        String headerAuth = request.getHeader("Authorization");
        Clinician clinician = this.webClientBuilder.build().get()
                .uri("http://clinician-management/api/users")
                .attribute("id", id)
                .header("Authorization", headerAuth)
                .retrieve()
                .bodyToMono(Clinician.class)
                .block();
        return clinician;
    }

    private Patient updateData(final Patient patient, final PatientData.Edit data) {
        return patient.toBuilder()
                .firstName(data.getFirstName())
                .lastName(data.getLastName())
                .photo(data.getPhoto())
                .status(data.getStatus())
                .email(data.getEmail())
                .gender(data.getGender())
                .birthDate(data.getBirthDate())
                .address(data.getAddress())
                .city(data.getCity())
                .country(data.getCountry())
                .zipCode(data.getZipCode())
                .region(data.getRegion())
                .build();
    }
}
