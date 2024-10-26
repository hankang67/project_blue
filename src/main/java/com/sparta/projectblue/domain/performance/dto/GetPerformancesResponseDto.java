package com.sparta.projectblue.domain.performance.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetPerformancesResponseDto {

    private String title;
    private String hallNm;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
