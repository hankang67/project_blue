package com.sparta.projectblue.domain.common.enums;

import lombok.Getter;

@Getter
public enum PaymentStatus {
    DONE("성공"),
    CANCELED("취소");

    private final String value;

    PaymentStatus(String value) {
        this.value = value;
    }

}
