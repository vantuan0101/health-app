package com.vantuan.careplanmanagement.repository;

import com.vantuan.careplanmanagement.model.entity.Medication;
import com.vantuan.framework.crud.respository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationRepository extends BaseRepository<Medication, Long> {
}
