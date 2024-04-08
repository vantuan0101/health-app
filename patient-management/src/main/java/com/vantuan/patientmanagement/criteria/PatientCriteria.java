package com.vantuan.patientmanagement.criteria;

import com.vantuan.crud.respository.BaseDAO;
import com.vantuan.patientmanagement.common.address.model.entity.UserAddress;
import com.vantuan.patientmanagement.enums.Country;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PatientCriteria extends BaseDAO {

    private Long patientId;

    private String firstName;

    private String lastName;

    private String photo;

    private LocalDate birthDate;

    private UserAddress address;

    private Country country;

    private Short height;

    private Float weight;
}

