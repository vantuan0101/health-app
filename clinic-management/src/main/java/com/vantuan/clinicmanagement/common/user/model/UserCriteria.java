package com.vantuan.patientmanagement.common.user.model;

import com.vantuan.patientmanagement.common.enums.UserRole;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserCriteria {
    private String email;
    private String password;
    private String confirmPass;
    private UserRole userRole;
}
