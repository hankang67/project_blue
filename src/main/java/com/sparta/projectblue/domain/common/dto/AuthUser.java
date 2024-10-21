package com.sparta.projectblue.domain.common.dto;

import java.util.Collection;
import java.util.List;

import com.sparta.projectblue.domain.common.enums.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Getter;

@Getter
public class AuthUser {

    private final Long id;
    private final String email;
    private final String name;
    private final Collection<? extends GrantedAuthority> authorities;

    public AuthUser(Long id, String email, String name, UserRole userRole) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.authorities = List.of(new SimpleGrantedAuthority(userRole.name()));
    }
}
