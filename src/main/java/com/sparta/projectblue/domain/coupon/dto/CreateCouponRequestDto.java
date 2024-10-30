package com.sparta.projectblue.domain.coupon.dto;

import java.time.LocalDateTime;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

import com.sparta.projectblue.domain.common.enums.CouponStatus;
import com.sparta.projectblue.domain.common.enums.CouponType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCouponRequestDto {

    @NotNull private String couponCode;

    @Enumerated(EnumType.STRING)
    @NotNull
    private CouponType type;

    @Enumerated(EnumType.STRING)
    @NotNull
    private CouponStatus status;

    @NotNull private int maxQuantity;

    @NotNull private int currentQuantity;

    @NotNull private Long discountValue;

    @NotNull private LocalDateTime startDate;

    @NotNull private LocalDateTime endDate;
}
