package com.vantuan.careplanmanagement.repository;

import com.vantuan.careplanmanagement.model.entity.Allergy;
import com.vantuan.framework.crud.respository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllergyRepository extends BaseRepository<Allergy, Long> {
}
