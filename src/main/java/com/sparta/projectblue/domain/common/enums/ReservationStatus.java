package com.sparta.projectblue.domain.common.enums;

public enum ReservationStatus {
    PENDING("결제대기"),
    COMPLETED("예매완료"),
    CANCELED("예매취소");

    private final String status;

    ReservationStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
