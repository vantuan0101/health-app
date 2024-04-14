package com.vantuan.careplanmanagement.common.user.model;

import lombok.Getter;
import lombok.Setter;

import com.vantuan.careplanmanagement.common.enums.UserRole;

@Getter
@Setter
public class UserCriteria {
    private String email;
    private String password;
    private String confirmPass;
    private UserRole userRole;
}
