package com.sparta.projectblue.domain.reservation.dto;

import java.time.LocalDateTime;

import com.sparta.projectblue.domain.common.enums.ReservationStatus;

import lombok.Getter;

@Getter
public class GetReservationsResponseDto {

    private final String performanceTitle;
    private final int tickets;
    private final Long reservationId;
    private final LocalDateTime reservationDate;
    private final String HallName;
    private final LocalDateTime round;
    private final ReservationStatus status;

    public GetReservationsResponseDto(
            String performanceTitle,
            int tickets,
            Long reservationId,
            LocalDateTime reservationDate,
            String hallName,
            LocalDateTime round,
            ReservationStatus status) {

        this.performanceTitle = performanceTitle;
        this.tickets = tickets;
        this.reservationId = reservationId;
        this.reservationDate = reservationDate;
        this.HallName = hallName;
        this.round = round;
        this.status = status;
    }
}
