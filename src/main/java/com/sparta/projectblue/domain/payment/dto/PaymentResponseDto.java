package com.sparta.projectblue.domain.payment.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PaymentResponseDto {

    private String orderId;
    private String orderName;
    private Long amount;
    private String customerEmail;
    private String customerName;
    private Long discountAmount;

    public PaymentResponseDto(
            String orderId,
            String orderName,
            Long amount,
            String customerEmail,
            String customerName,
            Long discountAmount) {

        this.orderId = orderId;
        this.orderName = orderName;
        this.amount = amount;
        this.customerEmail = customerEmail;
        this.customerName = customerName;
        this.discountAmount = discountAmount;
    }
}
