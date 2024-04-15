package com.vantuan.patientmanagement.security;

import io.vavr.control.Option;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

/**
 * Authenticated user info object retrieved from id token
 */
@Data
@Builder(toBuilder = true)
public class AuthenticatedUser {

    @NonNull
    private Boolean emailVerified;

    @NonNull
    private String email;

    @NonNull
    private String username;

    @NonNull
    private Long userID;

    @NonNull
    private List<UserRoles> userRoles;

    @NonNull
    private Option<String> clinicianUsername;

    @NonNull
    private Option<Long> clinicianUserId;

}
