package com.sparta.projectblue.domain.reservation.entity;

import com.sparta.projectblue.domain.common.entity.BaseEntity;

import com.sparta.projectblue.domain.payment.entity.Payment;
import com.sparta.projectblue.domain.performance.entity.Performance;

import com.sparta.projectblue.domain.common.enums.ReservationStatus;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "reservations")
public class Reservation extends BaseEntity {

    @Column(nullable = false, name = "user_id")
    private Long userId;

    @Column(name = "payment_id")
    private Long paymentId;

    @Column(nullable = false, name = "performance_id")
    private Long performanceId;

    @Column(nullable = false, name = "round_id")
    private Long roundId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private ReservationStatus status;

    @Column(nullable = false)
    private int price;

    public Reservation(Long userId, Long performanceId, Long roundId, ReservationStatus status, int price) {
        this.userId = userId;
        this.performanceId = performanceId;
        this.roundId = roundId;
        this.status = status;
        this.price = price;
    }

    public void addPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public void resCanceled(){
        this.status = ReservationStatus.CANCELED;
    }

    public void resCompleted() {
        this.status = ReservationStatus.COMPLETED;
    }

}
