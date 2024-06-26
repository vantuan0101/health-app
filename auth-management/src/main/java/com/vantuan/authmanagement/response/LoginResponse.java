package com.vantuan.authmanagement.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String message;
    private Boolean success;
    private String token;
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private List<String> roles;

    public LoginResponse(String message, Boolean success) {
        this.message = message;
        this.success = success;
        this.token = null;

    }

    public LoginResponse(String message, String token, Long id, String username,
            String email, String firstName, String lastName, List<String> roles) {
        this.message = message;
        this.success = true;
        this.token = token;
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roles = roles;
    }

    public LoginResponse(String message, Boolean success, String token) {
        this.message = message;
        this.success = success;
        this.token = token;
    }

    public static LoginResponse fail(String message) {
        return new LoginResponse(message, false);
    }
}
