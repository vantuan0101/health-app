package com.vantuan.patientmanagement.criteria;

import com.vantuan.patientmanagement.common.address.model.entity.UserAddress;
import com.vantuan.patientmanagement.common.enums.Country;
import com.vantuan.patientmanagement.common.user.model.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PatientCriteria {

    private Long id;

    private String email;

    private String firstName;

    private String lastName;

    private String photo;

    private LocalDate birthDate;

    private UserAddress address;

    private Country country;

    private Short height;

    private Float weight;

    private User user;
}
