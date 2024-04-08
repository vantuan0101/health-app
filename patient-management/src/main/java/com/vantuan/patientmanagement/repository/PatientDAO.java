package com.vantuan.patientmanagement.repository;

import com.vantuan.common.exception.ResourceNotFoundException;
import com.vantuan.crud.respository.BaseDAO;
import com.vantuan.patientmanagement.criteria.PatientCriteria;
import com.vantuan.patientmanagement.model.entity.Patient;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.constraints.NotNull;


@Component
public class PatientDAO extends BaseDAO<Patient, Long, PatientCriteria> {

    @Transactional
    public Patient getByIdOrThrow(@NotNull final Long id) {
        final PatientCriteria criteria = new PatientCriteria();
        criteria.setPatientId(id);
        return getOne(criteria).orElseThrow(() -> new ResourceNotFoundException((Object) id));
    }
}

