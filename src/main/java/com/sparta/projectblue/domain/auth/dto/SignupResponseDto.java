package com.sparta.projectblue.domain.auth.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sparta.projectblue.domain.coupon.entity.Coupon;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SignupResponseDto {

    private final String bearerToken;
    private final Coupon coupon;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;

    public SignupResponseDto(String bearerToken, Coupon coupon) {

        this.bearerToken = bearerToken;
        this.coupon = coupon;
        this.createdAt = coupon.getCreatedAt();
    }
}