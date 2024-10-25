package com.sparta.projectblue.domain.reservation.dto;

import com.sparta.projectblue.domain.common.enums.ReservationStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class CreateReservationDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        @NotNull
        private Long roundId;

        @NotNull
        private List<Integer> seats;
    }

    @Getter
    public static class Response {
        private final Long id;
        private final String performanceTitle;
        private final LocalDateTime roundDate;
        private final List<Integer> seats;
        private final Long price;
        private final ReservationStatus status;

        public Response(Long id, String performanceTitle, LocalDateTime roundDate, List<Integer> seats, Long price, ReservationStatus status) {
            this.id = id;
            this.performanceTitle = performanceTitle;
            this.roundDate = roundDate;
            this.seats = seats;
            this.price = price;
            this.status = status;
        }
    }
}
