package com.vantuan.patientmanagement.service;

import com.vantuan.common.exception.ValidationException;
import com.vantuan.common.utils.ValidationTypeUtil;
import com.vantuan.common.utils.exception.ExceptionHelper;
import com.vantuan.crud.respository.BaseRepository;
import com.vantuan.crud.service.BaseService;
import com.vantuan.patientmanagement.common.user.model.User;
import com.vantuan.patientmanagement.criteria.PatientCriteria;
import com.vantuan.patientmanagement.model.entity.Patient;
import com.vantuan.patientmanagement.repository.PatientRepository;
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
public class PatientService extends BaseService<Patient, PatientCriteria> {
    private PatientRepository patientRepository;
    private WebClient.Builder webClientBuilder;

    @Autowired
    public PatientService(PatientRepository patientRepository, WebClient.Builder webClientBuilder) {
        this.patientRepository = patientRepository;
        this.webClientBuilder = webClientBuilder;

    }

    @Override
    protected BaseRepository<Patient> getRepository() {
        return this.patientRepository;
    }

    @Override
    protected void validateDto(ValidationTypeUtil validationTypeUtil, PatientCriteria dto) throws ValidationException {
        ExceptionHelper exceptionHelper = new ExceptionHelper();
        /** Check error */
        if (!exceptionHelper.isEmpty()) {
            throw new ValidationException(exceptionHelper.getMessage());
        }
    }

    public User getUserEmail(HttpServletRequest request, @Nonnull String email) {
        String headerAuth = request.getHeader("Authorization");
        User user = this.webClientBuilder.build().get()
                .uri("http://auth-management/api/users")
                .attribute("email", email)
                .header("Authorization", headerAuth)
                .retrieve()
                .bodyToMono(User.class)
                .block();
        return user;
    }
}
