package com.sparta.projectblue.domain.auth.dto;

import lombok.Getter;

@Getter
public class SigninResponseDto {

    private final String bearerToken;

    public SigninResponseDto(String bearerToken) {

        this.bearerToken = bearerToken;
    }
}
