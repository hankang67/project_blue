package com.sparta.projectblue.domain.round.dto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class GetAvailableSeatsDto {

    @Getter
    public static class Response {
        private final String PerformanceTitle;
        private final LocalDateTime roundDate;
        private final List<Integer> seats;

        public Response(String performanceTitle, LocalDateTime roundDate, List<Integer> seats) {
            PerformanceTitle = performanceTitle;
            this.roundDate = roundDate;
            this.seats = seats;
        }
    }
}
