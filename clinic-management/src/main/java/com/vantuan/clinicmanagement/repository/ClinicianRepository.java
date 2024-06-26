package com.vantuan.clinicmanagement.repository;

import com.vantuan.clinicmanagement.model.entity.Clinician;
import com.vantuan.framework.crud.respository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicianRepository extends BaseRepository<Clinician, Long> {
}
