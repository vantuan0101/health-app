package com.vantuan.careplanmanagement.service;

import com.vantuan.careplanmanagement.criteria.AllergyCriteria;
import com.vantuan.careplanmanagement.model.entity.Allergy;
import com.vantuan.careplanmanagement.model.entity.Patient;
import com.vantuan.careplanmanagement.repository.AllergyRepository;
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
public class AllergyService extends BaseService<Allergy, AllergyCriteria> {
    private AllergyRepository allergyRepository;
    private WebClient.Builder webClientBuilder;

    @Autowired
    public AllergyService(AllergyRepository allergyRepository, WebClient.Builder webClientBuilder) {
        this.allergyRepository = allergyRepository;
        this.webClientBuilder = webClientBuilder;

    }

    @Override
    protected BaseRepository<Allergy> getRepository() {
        return this.allergyRepository;
    }

    @Override
    protected void validateDto(ValidationTypeUtil validationTypeUtil, AllergyCriteria dto) throws ValidationException {
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