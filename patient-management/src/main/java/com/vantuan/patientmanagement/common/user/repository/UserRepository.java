package com.vantuan.patientmanagement.common.user.repository;

import com.vantuan.crud.respository.BaseRepository;
import com.vantuan.patientmanagement.common.user.model.User;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User> {
    Optional<User> findByEmail(String email);
}
