package com.sparta.projectblue.domain.performance.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PerformanceResponseDto {
    private String title;
    private String hallNm;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public PerformanceResponseDto(String title, String hallNm, LocalDateTime startDate, LocalDateTime endDate) {
        this.title = title;
        this.hallNm = hallNm;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
