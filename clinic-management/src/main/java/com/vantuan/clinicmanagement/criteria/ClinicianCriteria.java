package com.vantuan.clinicmanagement.criteria;

import com.vantuan.clinicmanagement.common.enums.Country;
import com.vantuan.clinicmanagement.common.enums.Gender;
import com.vantuan.clinicmanagement.common.enums.Region;
import com.vantuan.clinicmanagement.model.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ClinicianCriteria {

    private Long id;

    private Boolean active;

    private Boolean isVerified;

    private String firstName;

    private String lastName;

    private String email;

    private LocalDate birthDate;

    private String userId;

    private User user;

    private String address;

    private String city;

    private Country country;

    private String zipCode;

    private Region region;
    private Gender gender;
}
