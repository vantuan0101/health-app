package com.vantuan.clinicmanagement.repository;

import org.springframework.stereotype.Component;
import com.vantuan.clinicmanagement.criteria.ClinicianCriteria;
import com.vantuan.clinicmanagement.model.entity.Clinician;
import com.vantuan.crud.respository.BaseDAO;

@Component
public class ClinicianDAO extends BaseDAO<Clinician, Long, ClinicianCriteria> {
}
