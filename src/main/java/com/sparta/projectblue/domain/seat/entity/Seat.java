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
@Table(name = "seats")
public class Seat extends BaseEntity {

    @Column(nullable = false)
    private Long reservationId;

    @Column(nullable = false)
    private Long seatNumber;

    public Seat(Long reservationId, Long seatNumber) {
        this.reservationId = reservationId;
        this.seatNumber = seatNumber;
    }
}
