package com.sparta.projectblue.domain.payment.entity;

import com.sparta.projectblue.domain.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "payments")
public class Payment extends BaseEntity {

    @Column(nullable = false, length = 20, name = "payment_type")
    private String paymentType;

    @Column(nullable = false, length = 50, name = "transaction_id")
    private String transactionId;

    @Column(nullable = false, length = 20, name = "payment_method")
    private String paymentMethod;

    @Column(nullable = false, name = "payment_price")
    private int paymentPrice;

    @Column(nullable = false, name = "payment_time")
    private LocalDateTime paymentTime;

    public Payment(String paymentType, String transactionId, String paymentMethod, int paymentPrice, LocalDateTime paymentTime) {
        this.paymentType = paymentType;
        this.transactionId = transactionId;
        this.paymentMethod = paymentMethod;
        this.paymentPrice = paymentPrice;
        this.paymentTime = paymentTime;
    }
}
