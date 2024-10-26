package com.sparta.projectblue.domain.payment.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

import com.sparta.projectblue.domain.common.entity.BaseEntity;
import com.sparta.projectblue.domain.common.enums.PaymentStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "payments")
public class Payment extends BaseEntity {

    @Column(nullable = false, name = "user_id")
    private Long userId;

    @Column(nullable = false, name = "reservation_id")
    private Long reservationId;

    @Column(nullable = false, name = "performance_id")
    private Long performanceId;

    @Column(length = 255, name = "payment_key")
    private String paymentKey;

    @Column(length = 20)
    private String type; // 결제 종류 : NORMAL, BILLING, BRANDPAY

    @Column(length = 20)
    private String method; // 결제 수단 : 칻, 가상계좌, 간편결제, 휴대폰, 계좌이체, 문화상품권, 도서상품권, 게임문화상품권

    @Column(name = "amount_supplied")
    private Long amountSupplied; // 결제 수수료의 공급 가액

    @Column(name = "amount_vat")
    private Long amountVat; // 수수료

    @Column(nullable = false, name = "amount_total")
    private Long amountTotal; // suppliedAmount + vat

    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Column(name = "approved_at")
    private LocalDateTime approvedAt;

    @Column(nullable = false, length = 30, name = "order_id")
    private String orderId;

    public Payment(
            Long userId, Long reservationId, Long performanceId, Long amountTotal, String orderId) {
        this.userId = userId;
        this.reservationId = reservationId;
        this.performanceId = performanceId;
        this.amountTotal = amountTotal;
        this.orderId = orderId;
        this.status = PaymentStatus.READY;
    }

    public void addPaymentInfo(
            String paymentKey,
            String type,
            String method,
            Long amountSupplied,
            Long amountVat,
            LocalDateTime approvedAt) {
        this.paymentKey = paymentKey;
        this.type = type;
        this.method = method;
        this.amountSupplied = amountSupplied;
        this.amountVat = amountVat;
        this.status = PaymentStatus.DONE;
        this.approvedAt = approvedAt;
    }

    public void canceled() {
        this.status = PaymentStatus.CANCELED;
    }
}
