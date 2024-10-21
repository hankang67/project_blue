package com.sparta.projectblue.domain.reservation.entity;

import com.sparta.projectblue.domain.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "reservations")
public class Reservation extends BaseEntity {

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = true)
    private Long paymentId;

    @Column(nullable = false)
    private Long performanceId;

    @Column(nullable = false)
    private Long roundId;

    @Column(nullable = false, length = 20)
    private String status;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int seatNumber;

    public Reservation(Long userId, Long paymentId, Long performanceId, Long roundId, String status, int price, int seatNumber) {
        this.userId = userId;
        this.paymentId = paymentId;
        this.performanceId = performanceId;
        this.roundId = roundId;
        this.status = status;
        this.price = price;
        this.seatNumber = seatNumber;
    }
}
