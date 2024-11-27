package com.sparta.projectblue.domain.common.enums;

import lombok.Getter;

@Getter
public enum CouponType {
    AMOUNT("금액 할인 쿠폰"),
    RATE("퍼센트 할인 쿠폰");

    private final String type;

    CouponType(String type) {
        this.type = type;
    }
}
