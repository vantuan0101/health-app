package com.vantuan.careplanmanagement.repository;

import com.vantuan.careplanmanagement.criteria.AllergyCriteria;
import com.vantuan.careplanmanagement.model.entity.Allergy;
import com.vantuan.crud.respository.BaseDAO;
import org.springframework.stereotype.Component;

@Component
public class AllergyDAO extends BaseDAO<Allergy, Long, AllergyCriteria> {
}
