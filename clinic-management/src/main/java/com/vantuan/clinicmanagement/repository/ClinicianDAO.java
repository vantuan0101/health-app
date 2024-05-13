package com.vantuan.clinicmanagement.repository;

import com.vantuan.framework.crud.respository.BaseDAO;
import org.springframework.stereotype.Component;
import com.vantuan.clinicmanagement.criteria.ClinicianCriteria;
import com.vantuan.clinicmanagement.model.entity.Clinician;

@Component
public class ClinicianDAO extends BaseDAO<Clinician, Long, ClinicianCriteria> {
}
