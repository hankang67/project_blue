package com.sparta.projectblue.domain.payment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PaymentResponseDto {
    private String payType;
    private String orderId;
    private String orderName;
    private Long amount;
    private String customerEmail;
    private String customerName;

    public PaymentResponseDto(String payType, String orderId, String orderName, Long amount, String customerEmail, String customerName) {
        this.payType = payType;
        this.orderId = orderId;
        this.orderName = orderName;
        this.amount = amount;
        this.customerEmail = customerEmail;
        this.customerName = customerName;
    }
}
