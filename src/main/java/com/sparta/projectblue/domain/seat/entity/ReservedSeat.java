package com.sparta.projectblue.domain.seat.entity;

import com.sparta.projectblue.domain.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "reserved_seats")
public class ReservedSeat extends BaseEntity {

    @Column(nullable = false)
    private Long reservationId;

    @Column(nullable = false)
    private Long roundId;

    @Column(nullable = false)
    private int seatNumber;

    public ReservedSeat(Long reservationId, Long roundId, int seatNumber) {
        this.reservationId = reservationId;
        this.roundId = roundId;
        this.seatNumber = seatNumber;
    }
}
