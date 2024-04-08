package com.vantuan.patientmanagement.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum UserRoles {

    ROLE_PATIENT("ROLE_PATIENT"),
    ROLE_CLINICIAN("ROLE_CLINICIAN"),
    ROLE_ADMIN("ROLE_ADMIN");

    @Getter
    private final String role;

}
