package com.vantuan.careplanmanagement.repository;

import com.vantuan.careplanmanagement.model.entity.PhysicalActivity;
import com.vantuan.framework.crud.respository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhysicalActivityRepository extends BaseRepository<PhysicalActivity, Long> {
}