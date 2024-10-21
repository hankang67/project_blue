package com.sparta.projectblue.domain.common.enums;

public enum PerformanceStatus {
    BEFORE_OPEN("예매오픈전"),
    AVAILABLE("예매가능"),
    SOLD_OUT("매진");

    private final String status;

    PerformanceStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
