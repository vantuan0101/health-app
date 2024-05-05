package com.vantuan.careplanmanagement.repository;

import com.vantuan.careplanmanagement.criteria.VitalCriteria;
import com.vantuan.careplanmanagement.model.entity.Vital;
import com.vantuan.crud.respository.BaseDAO;
import org.springframework.stereotype.Component;

@Component
public class VitalDAO extends BaseDAO<Vital, Long, VitalCriteria> {
}
