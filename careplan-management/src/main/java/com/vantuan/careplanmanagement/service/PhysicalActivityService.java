package com.vantuan.careplanmanagement.service;

import com.vantuan.careplanmanagement.criteria.PhysicalActivityCriteria;
import com.vantuan.careplanmanagement.model.entity.Patient;
import com.vantuan.careplanmanagement.model.entity.PhysicalActivity;
import com.vantuan.careplanmanagement.repository.PhysicalActivityRepository;
import com.vantuan.common.exception.ValidationException;
import com.vantuan.common.utils.ValidationTypeUtil;
import com.vantuan.common.utils.exception.ExceptionHelper;
import com.vantuan.crud.respository.BaseRepository;
import com.vantuan.crud.service.BaseService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.Nonnull;

@Slf4j
@Service
@RequiredArgsConstructor
public class PhysicalActivityService extends BaseService<PhysicalActivity, PhysicalActivityCriteria> {
    private PhysicalActivityRepository physicalActivityRepository;
    private WebClient.Builder webClientBuilder;

    @Autowired
    public PhysicalActivityService(PhysicalActivityRepository physicalActivityRepository,
            WebClient.Builder webClientBuilder) {
        this.physicalActivityRepository = physicalActivityRepository;
        this.webClientBuilder = webClientBuilder;

    }

    @Override
    protected BaseRepository<PhysicalActivity> getRepository() {
        return this.physicalActivityRepository;
    }

    @Override
    protected void validateDto(ValidationTypeUtil validationTypeUtil, PhysicalActivityCriteria dto)
            throws ValidationException {
        ExceptionHelper exceptionHelper = new ExceptionHelper();
        /** Check error */
        if (!exceptionHelper.isEmpty()) {
            throw new ValidationException(exceptionHelper.getMessage());
        }
    }

    public Patient getPatient(HttpServletRequest request, @Nonnull Long patientId) {
        String headerAuth = request.getHeader("Authorization");
        Patient patient = this.webClientBuilder.build().get()
                .uri("http://patient-management/api/patient/" + patientId)
                .header("Authorization", headerAuth)
                .retrieve()
                .bodyToMono(Patient.class)
                .block();
        return patient;
    }
}