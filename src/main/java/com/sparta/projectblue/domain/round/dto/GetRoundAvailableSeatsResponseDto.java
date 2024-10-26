package com.sparta.projectblue.domain.round.dto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class GetRoundAvailableSeatsResponseDto {


    private final String PerformanceTitle;
    private final LocalDateTime roundDate;
    private final List<Integer> seats;

    public GetRoundAvailableSeatsResponseDto(String performanceTitle, LocalDateTime roundDate, List<Integer> seats) {
        PerformanceTitle = performanceTitle;
        this.roundDate = roundDate;
        this.seats = seats;
    }

}
