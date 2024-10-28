package com.sparta.projectblue.domain.coupon.dto;

import com.sparta.projectblue.domain.common.enums.CouponStatus;
import com.sparta.projectblue.domain.common.enums.CouponType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class CouponRequestDto {

    private final String couponCode;

    @Enumerated(EnumType.STRING)
    private final CouponType type;

    @Enumerated(EnumType.STRING)
    private final CouponStatus status;

    private final int discountValue;

    private final LocalDateTime startDate;

    private final LocalDateTime endDate;

}
