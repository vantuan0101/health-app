package com.vantuan.patientmanagement.repository;

import com.vantuan.patientmanagement.criteria.PatientCriteria;
import com.vantuan.patientmanagement.model.entity.Patient;
import org.springframework.stereotype.Component;
import com.vantuan.crud.respository.BaseDAO;

@Component
public class PatientDAO extends BaseDAO<Patient, Long, PatientCriteria> {
}
