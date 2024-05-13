package com.vantuan.careplanmanagement.repository;

import com.vantuan.careplanmanagement.criteria.MedicationCriteria;
import com.vantuan.careplanmanagement.model.entity.Medication;
import com.vantuan.crud.respository.BaseDAO;
import org.springframework.stereotype.Component;

@Component
public class MedicationDAO extends BaseDAO<Medication, Long, MedicationCriteria> {
}
