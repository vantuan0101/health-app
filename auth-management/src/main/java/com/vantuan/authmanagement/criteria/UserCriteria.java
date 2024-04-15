package com.vantuan.authmanagement.criteria;

import com.vantuan.authmanagement.enums.UserRole;
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
