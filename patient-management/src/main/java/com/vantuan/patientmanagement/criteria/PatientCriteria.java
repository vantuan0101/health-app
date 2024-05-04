package com.vantuan.patientmanagement.criteria;

import com.vantuan.patientmanagement.common.enums.Country;
import com.vantuan.patientmanagement.common.enums.Region;
import com.vantuan.patientmanagement.model.entity.Clinician;
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

    private String address;

    private String city;

    private Country country;

    private String zipCode;

    private Region region;

    private Short height;

    private Float weight;

    private Long clinicianId;

    private Clinician clinician;
}
