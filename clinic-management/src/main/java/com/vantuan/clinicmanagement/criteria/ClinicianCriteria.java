package com.vantuan.clinicmanagement.criteria;

import com.vantuan.clinicmanagement.common.enums.Country;
import com.vantuan.clinicmanagement.common.enums.Region;
import com.vantuan.clinicmanagement.common.user.model.User;
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
    private Long userId;
    private User user;
    private String address;

    private String city;

    private Country country;

    private String zipCode;

    private Region region;
}
