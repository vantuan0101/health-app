package com.vantuan.careplanmanagement.common.user.repository;

import com.vantuan.crud.respository.BaseRepository;
import com.vantuan.careplanmanagement.common.user.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User> {
    Optional<User> findByEmail(String email);
}
