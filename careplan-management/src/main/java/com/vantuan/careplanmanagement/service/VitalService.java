package com.vantuan.careplanmanagement.service;

import com.vantuan.careplanmanagement.criteria.VitalCriteria;
import com.vantuan.careplanmanagement.model.entity.Patient;
import com.vantuan.careplanmanagement.model.entity.Vital;
import com.vantuan.careplanmanagement.repository.VitalRepository;
import com.vantuan.common.exception.ValidationException;
import com.vantuan.common.utils.ValidationTypeUtil;
import com.vantuan.common.utils.exception.ExceptionHelper;
import com.vantuan.crud.respository.BaseRepository;
import com.vantuan.crud.service.BaseService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.Nonnull;

@Slf4j
@Service
public class VitalService extends BaseService<Vital, VitalCriteria> {
    private VitalRepository vitalRepository;
    private WebClient.Builder webClientBuilder;

    @Autowired
    public VitalService(VitalRepository vitalRepository, WebClient.Builder webClientBuilder) {
        this.vitalRepository = vitalRepository;
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    protected BaseRepository<Vital> getRepository() {
        return this.vitalRepository;
    }

    @Override
    protected void validateDto(ValidationTypeUtil validationTypeUtil, VitalCriteria dto) throws ValidationException {
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