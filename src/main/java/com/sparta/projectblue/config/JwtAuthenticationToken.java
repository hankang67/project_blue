package com.sparta.projectblue.config;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import com.sparta.projectblue.domain.common.dto.AuthUser;

import java.io.Serializable;

public class JwtAuthenticationToken extends AbstractAuthenticationToken implements Serializable {

    private static final long serialVersionUID = 1L; // 직렬화 버전 ID 추가
    private final transient AuthUser authUser; // `transient` 키워드 추가

    public JwtAuthenticationToken(AuthUser authUser) {
        super(authUser.getAuthorities());
        this.authUser = authUser;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return authUser;
    }
}
