package com.sparta.projectblue.domain.payment.entity;

import com.sparta.projectblue.domain.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "payments")
public class Payment extends BaseEntity {

    @Column(nullable = false, length = 20)
    private String paymentType;

    @Column(nullable = false, length = 50)
    private String transactionId;

    @Column(nullable = false, length = 20)
    private String paymentMethod;

    @Column(nullable = false)
    private int paymentPrice;

    @Column(nullable = false)
    private LocalDateTime paymentTime;

    public Payment(String paymentType, String transactionId, String paymentMethod, int paymentPrice, LocalDateTime paymentTime) {
        this.paymentType = paymentType;
        this.transactionId = transactionId;
        this.paymentMethod = paymentMethod;
        this.paymentPrice = paymentPrice;
        this.paymentTime = paymentTime;
    }
}
