package com.sparta.projectblue.domain.performance.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class GetPerformancesResponseDto {
    private String title;
    private String hallNm;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
