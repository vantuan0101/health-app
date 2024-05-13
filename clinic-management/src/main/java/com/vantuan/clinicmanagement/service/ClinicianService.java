package com.vantuan.clinicmanagement.service;

import com.vantuan.clinicmanagement.criteria.ClinicianCriteria;
import com.vantuan.clinicmanagement.common.exception.ResourceNotCreatedException;
import com.vantuan.clinicmanagement.common.exception.ResourceNotUpdatedException;
import com.vantuan.clinicmanagement.model.data.ClinicianData;
import com.vantuan.clinicmanagement.model.entity.Clinician;
import com.vantuan.clinicmanagement.model.entity.User;
import com.vantuan.clinicmanagement.repository.ClinicianDAO;

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
public class ClinicianService extends BaseService<Clinician, Long, ClinicianCriteria> {

    private final MappingUtil mappingUtil;
    private final ClinicianDAO clinicianDAO;
    private final WebClient.Builder webClientBuilder;

    @Transactional
    public Clinician create(final ClinicianData.Create data, HttpServletRequest request) {
        log.info("Creating clinician with userUuid : {}", data.getUserId());
        final User user = getUser(request, data.getUserId());
        data.setUser(user);
        return Try.of(() -> super.save(mappingUtil.map(data, Clinician.class)))
                .getOrElseThrow(ResourceNotCreatedException::new);
    }

    @Transactional
    public Clinician edit(final ClinicianData.Edit data, final Long id) {
        final Clinician clinician = clinicianDAO.get(id);
        log.info("Editing clinician with id : {}", id);
        return Try.of(() -> super.update(updateData(clinician, data)))
                .getOrElseThrow(ResourceNotUpdatedException::new);
    }

    public User getUser(HttpServletRequest request, @Nonnull String id) {
        String headerAuth = request.getHeader("Authorization");
        User user = webClientBuilder.build().get()
                .uri("http://auth-management/api/users", uriBuilder -> uriBuilder.path("/{id}").build(id))
                .header("Authorization", headerAuth)
                .retrieve()
                .bodyToMono(User.class)
                .block();
        return user;
    }

    private Clinician updateData(final Clinician clinician, final ClinicianData.Edit data) {
        return clinician.toBuilder()
                .isVerified(data.getIsVerified())
                .active(data.getActive())
                .firstName(data.getFirstName())
                .lastName(data.getLastName())
                .address(data.getAddress())
                .city(data.getCity())
                .country(data.getCountry())
                .zipCode(data.getZipCode())
                .region(data.getRegion())
                .birthDate(data.getBirthDate())
                .gender(data.getGender())
                .build();
    }
}