package com.vantuan.authmanagement.service;

import com.vantuan.authmanagement.config.UserDetailsImplement;
import com.vantuan.authmanagement.criteria.UserCriteria;
import com.vantuan.authmanagement.model.entity.User;
import com.vantuan.authmanagement.repository.UserRepository;
import com.vantuan.common.exception.ValidationException;
import com.vantuan.common.utils.ValidationTypeUtil;
import com.vantuan.common.utils.exception.ExceptionHelper;
import com.vantuan.crud.respository.BaseRepository;
import com.vantuan.crud.service.BaseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
public class UserService extends BaseService<User, UserCriteria> implements UserDetailsService {
    public static final String USERID = "USER_ID";
    public static final String USER_EMAIL = "USER_EMAIL";

    private final ModelMapper modelMapper;

    private UserRepository userRepository;

    public UserService() {
        this.modelMapper = new ModelMapper();
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected BaseRepository<User> getRepository() {
        return this.userRepository;
    }

    @Override
    protected void validateDto(ValidationTypeUtil validationTypeUtil, UserCriteria userCriteria)
            throws ValidationException {
        ExceptionHelper exceptionHelper = new ExceptionHelper();
        /** Check error */
        if (!exceptionHelper.isEmpty()) {
            throw new ValidationException(exceptionHelper.getMessage());
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Not found"));

        // Lưu USER_EMAIL vào session
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest().getSession();
        session.setAttribute(USER_EMAIL, user.getEmail());

        return new UserDetailsImplement(user);
    }

    public Long getSessionUserId(@NotNull HttpSession session) {
        return (Long) session.getAttribute(USERID);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
