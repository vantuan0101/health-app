package com.vantuan.careplanmanagement.repository;

import com.vantuan.careplanmanagement.model.entity.Vital;
import com.vantuan.crud.respository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VitalRepository extends BaseRepository<Vital, Long> {
}
