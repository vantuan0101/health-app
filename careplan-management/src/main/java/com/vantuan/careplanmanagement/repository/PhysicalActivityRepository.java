package com.vantuan.careplanmanagement.repository;

import com.vantuan.careplanmanagement.model.entity.PhysicalActivity;
import com.vantuan.crud.respository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhysicalActivityRepository extends BaseRepository<PhysicalActivity, Long> {
}