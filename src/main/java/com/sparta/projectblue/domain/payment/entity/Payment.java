package com.sparta.projectblue.domain.payment.entity;

import com.sparta.projectblue.domain.common.entity.BaseEntity;
import com.sparta.projectblue.domain.common.enums.PaymentStatus;
import com.sparta.projectblue.domain.payment.dto.PaymentResponseDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "payments")
public class Payment extends BaseEntity {

    @Column(nullable = false, length = 20, name = "pay_type")
    private String payType; // 결제 종류 : NORMAL, BILLING, BRANDPAY

    @Column(nullable = false, length = 20, name = "pay_method")
    private String payMethod; // 결제 수단 : 칻, 가상계좌, 간편결제, 휴대폰, 계좌이체, 문화상품권, 도서상품권, 게임문화상품권

    @Column(nullable = false, length = 20, name = "payment_key")
    private String paymentKey;

    @Column(nullable = false, name = "reservation_id")
    private Long reservationId;

    @Column(nullable = false, name = "performance_id")
    private Long performanceId;

    @Column(nullable = false, name = "total_amount")
    private Long totalAmount; // suppliedAmount + vat

    @Column(nullable = false, name = "supplied_amount")
    private Long suppliedAmount; // 결제 수수료의 공급 가액

    @Column(nullable = false)
    private Long vat; // 수수료

    @Column(nullable = false, name = "user_id")
    private Long userId;

    @Column(nullable = false, name = "approved_at")
    private LocalDateTime approvedAt;

    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;


}
