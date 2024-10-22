package com.sparta.projectblue.domain.reservation.dto;

import com.sparta.projectblue.domain.common.enums.ReservationStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class CreateReservationDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        @NotNull
        private Long roundId;

        @NotNull
        private int seatNumber;
    }

    @Getter
    public static class Response {
        private final Long id;
        private final String performanceTitle;
        private final LocalDateTime roundDate;
        private final int seatNumber;
        private final int price;
        private final ReservationStatus status;

        public Response(Long id, String performanceTitle, LocalDateTime roundDate, int seatNumber, int price, ReservationStatus status) {
            this.id = id;
            this.performanceTitle = performanceTitle;
            this.roundDate = roundDate;
            this.seatNumber = seatNumber;
            this.price = price;
            this.status = status;
        }
    }
}
