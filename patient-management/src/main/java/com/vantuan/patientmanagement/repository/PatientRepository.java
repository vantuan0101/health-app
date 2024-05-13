package com.vantuan.patientmanagement.repository;

import com.vantuan.framework.crud.respository.BaseRepository;
import com.vantuan.patientmanagement.model.entity.Patient;

import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends BaseRepository<Patient, Long> {

}
