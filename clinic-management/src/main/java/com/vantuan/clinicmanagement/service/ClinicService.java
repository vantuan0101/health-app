package com.vantuan.clinicmanagement.service;

import com.vantuan.clinicmanagement.common.user.model.User;
import com.vantuan.clinicmanagement.criteria.ClinicianCriteria;
import com.vantuan.clinicmanagement.model.entity.Clinician;
import com.vantuan.clinicmanagement.repository.ClinicianRepository;
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
public class ClinicService extends BaseService<Clinician, ClinicianCriteria> {
    private ClinicianRepository clinicianRepository;
    private WebClient.Builder webClientBuilder;

    @Autowired
    public ClinicService(ClinicianRepository clinicianRepository, WebClient.Builder webClientBuilder) {
        this.clinicianRepository = clinicianRepository;
        this.webClientBuilder = webClientBuilder;

    }

    @Override
    protected BaseRepository<Clinician> getRepository() {
        return this.clinicianRepository;
    }

    @Override
    protected void validateDto(ValidationTypeUtil validationTypeUtil, ClinicianCriteria dto)
            throws ValidationException {
        ExceptionHelper exceptionHelper = new ExceptionHelper();
        /** Check error */
        if (!exceptionHelper.isEmpty()) {
            throw new ValidationException(exceptionHelper.getMessage());
        }
    }

    public User getUser(HttpServletRequest request, @Nonnull Long id) {
        String headerAuth = request.getHeader("Authorization");
        User user = this.webClientBuilder.build().get()
                .uri("http://auth-management/api/users",uriBuilder->uriBuilder.path("/{id}").build(id))
                .header("Authorization", headerAuth)
                .retrieve()
                .bodyToMono(User.class)
                .block();
        return user;
    }
}