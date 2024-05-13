package com.vantuan.authmanagement.repository;

import com.vantuan.authmanagement.model.entity.User;
import com.vantuan.framework.crud.respository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
