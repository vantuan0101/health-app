package com.vantuan.clinicmanagement.common.user.model;

import com.vantuan.clinicmanagement.common.enums.UserRole;
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
