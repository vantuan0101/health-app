package com.vantuan.authmanagement.config;

public final class Constants {

    private Constants() {
    }

    public static final String MESSAGE_INVALID_TOKEN = "Invalid JWT token: {}";
    public static final String MESSAGE_TOKEN_EXPIRED = "JWT token is expired: {}";
    public static final String MESSAGE_TOKEN_UNSUPPORTED = "JWT token is unsupported: {}";
    public static final String MESSAGE_TOKEN_CLAIM_EMPTY = "JWT claims string is empty: {}";

    public static final String MESSAGE_REGISTER_WELCOME = "Registered! Welcome.";
    public static final String MESSAGE_LOGIN_SUCCESS = "Login Successfully!.";
    public static final String MESSAGE_INVALID_USERNAME = "Invalid username.";
    public static final String MESSAGE_INVALID_PASSWORD = "Invalid password.";
    public static final String MESSAGE_INVALID_MATCH_PASSWORD = "Passwords don't match.";

    public static final int FIRST_NAME_MAX_SIZE = 150;
    public static final int LAST_NAME_MAX_SIZE = 150;

}
