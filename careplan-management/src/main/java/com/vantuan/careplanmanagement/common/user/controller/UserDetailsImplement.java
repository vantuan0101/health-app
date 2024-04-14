package com.vantuan.careplanmanagement.common.user.controller;

import java.util.Collection;
import java.util.Collections;

import com.vantuan.careplanmanagement.common.user.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDetailsImplement implements UserDetails {
    private static final long serialVersionUID = -1352733651057286866L;

    private User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(user.getUserRole()));
    }

    public Long getId() {
        return user.getId();
    }

    public String getEmail() {
        return user.getEmail();
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

}
