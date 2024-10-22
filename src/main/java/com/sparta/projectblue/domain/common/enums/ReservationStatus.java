package com.sparta.projectblue.domain.common.enums;

public enum ReservationStatus {
    FINISHED("예매완료"),
    CANCLED("예매취소");

    private final String status;


    ReservationStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}

