package com.vantuan.patientmanagement.clinician.repository;

import com.google.common.collect.Sets;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.StringExpression;
import com.vantuan.common.exception.ResourceNotFoundException;
import com.vantuan.crud.respository.BaseDAO;
import com.vantuan.patientmanagement.clinician.criteria.ClinicianCriteria;
import com.vantuan.patientmanagement.clinician.model.entity.Clinician;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;


@Component
public class ClinicianDAO extends BaseDAO<Clinician, Long, ClinicianCriteria> {

    @Transactional
    public Clinician getClinicianId(@NotNull final Long id) {
        final ClinicianCriteria criteria = new ClinicianCriteria();
        criteria.setClinicianId(id);

        return getOne(criteria).orElseThrow(() -> new ResourceNotFoundException((Object) id));
    }

//    @Transactional
//    public Clinician getByUserUuidOrThrow(@NotNull final String userUuid) {
//        final ClinicianCriteria criteria = new ClinicianCriteria();
//        criteria.setUserUuid(userUuid);
//        return getOne(criteria).orElseThrow(() -> new ResourceNotFoundException((Object) userUuid));
//    }

}
