package com.vantuan.careplanmanagement.repository;

import com.vantuan.careplanmanagement.model.entity.Vital;
import com.vantuan.framework.crud.respository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VitalRepository extends BaseRepository<Vital, Long> {
}
