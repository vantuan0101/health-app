package com.vantuan.clinicmanagement.criteria;

import com.vantuan.clinicmanagement.common.user.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClinicianCriteria {

    private Long clinicianId;
    private Boolean active;
    private Boolean isVerified;
    private String firstName;
    private String lastName;
    private String email;
    private User user;
}
