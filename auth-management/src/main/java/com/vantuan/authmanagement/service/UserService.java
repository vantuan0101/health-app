package com.vantuan.authmanagement.service;

import com.vantuan.authmanagement.config.UserDetailsImplement;
import com.vantuan.authmanagement.criteria.UserCriteria;
import com.vantuan.authmanagement.common.exception.ResourceNotCreatedException;
import com.vantuan.authmanagement.common.exception.ResourceNotUpdatedException;
import com.vantuan.authmanagement.model.data.UserData;
import com.vantuan.authmanagement.model.entity.User;
import com.vantuan.authmanagement.repository.UserDAO;
import com.vantuan.authmanagement.repository.UserRepository;
import com.vantuan.common.mapper.MappingUtil;
import com.vantuan.crud.service.BaseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import io.vavr.control.Try;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService extends BaseService<User, Long, UserCriteria> implements UserDetailsService {
    public static final String USERID = "USER_ID";
    public static final String USER_EMAIL = "USER_EMAIL";
    private final UserRepository userRepository;

    private final MappingUtil mappingUtil;

    private final UserDAO userDAO;

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

    @Transactional
    public User create(final UserData.Create data) {
        log.info("Creating user : {}");
        return Try.of(() -> super.save(mappingUtil.map(data, User.class)))
                .getOrElseThrow(ResourceNotCreatedException::new);
    }

    @Transactional
    public User edit(final UserData.Edit data, final Long id) {
        final User user = userDAO.get(id);
        log.info("Editing clinician with id : {}", id);
        return Try.of(() -> super.update(updateData(user, data)))
                .getOrElseThrow(ResourceNotUpdatedException::new);
    }

    private User updateData(final User user, final UserData.Edit data) {
        return user.toBuilder()
                .firstName(data.getFirstName())
                .lastName(data.getFirstName())
                .build();
    }
}
