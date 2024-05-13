package com.vantuan.patientmanagement.repository;

import com.vantuan.framework.crud.respository.BaseDAO;
import com.vantuan.patientmanagement.criteria.PatientCriteria;
import com.vantuan.patientmanagement.model.entity.Patient;
import org.springframework.stereotype.Component;

@Component
public class PatientDAO extends BaseDAO<Patient, Long, PatientCriteria> {
}
