package com.sparta.projectblue.domain.common.enums;

import lombok.Getter;

@Getter
public enum CouponStatus {
    ACTIVE("쿠폰 활성화"),
    EXPIRED("쿠폰 만료"),
    USED("쿠폰 사용됨");

    private final String status;

    CouponStatus(String status) {
        this.status = status;
    }
}
