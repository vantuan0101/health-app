package com.vantuan.patientmanagement.repository;

import com.vantuan.crud.respository.BaseRepository;
import com.vantuan.patientmanagement.model.entity.Patient;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository

public interface PatientRepository extends BaseRepository<Patient> {
    @Query(value = "SELECT * FROM #{#entityName} WHERE name = :name", nativeQuery = true)
    Set<Patient> findByName(String name);
}
