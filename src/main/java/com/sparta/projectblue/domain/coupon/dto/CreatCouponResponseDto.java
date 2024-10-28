package com.sparta.projectblue.domain.coupon.dto;

import com.sparta.projectblue.domain.common.enums.CouponStatus;
import com.sparta.projectblue.domain.common.enums.CouponType;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreatCouponResponseDto {

    private final Long id;
    private final String code;
    private final CouponStatus status;
    private final CouponType type;
    private final int discountValue;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public CreatCouponResponseDto(
            Long id,
            String code,
            CouponStatus status,
            CouponType type,
            int discountValue,
            LocalDateTime startDate,
            LocalDateTime endDate) {
        this.id = id;
        this.code = code;
        this.status = status;
        this.type = type;
        this.discountValue = discountValue;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
