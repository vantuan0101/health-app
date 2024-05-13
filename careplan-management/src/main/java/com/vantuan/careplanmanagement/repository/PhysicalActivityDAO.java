package com.vantuan.careplanmanagement.repository;

import com.vantuan.careplanmanagement.criteria.PhysicalActivityCriteria;
import com.vantuan.careplanmanagement.model.entity.PhysicalActivity;
import com.vantuan.framework.crud.respository.BaseDAO;
import org.springframework.stereotype.Component;

@Component
public class PhysicalActivityDAO extends BaseDAO<PhysicalActivity, Long, PhysicalActivityCriteria> {
}
