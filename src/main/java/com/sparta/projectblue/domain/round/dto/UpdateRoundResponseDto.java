package com.sparta.projectblue.domain.round.dto;

import com.sparta.projectblue.domain.common.enums.PerformanceStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateRoundResponseDto {
    private Long id;
    private Long performanceId;
    private LocalDateTime date;
    private PerformanceStatus status;

    public UpdateRoundResponseDto(Long id, Long performanceId, LocalDateTime date, PerformanceStatus status) {
        this.id = id;
        this.performanceId = performanceId;
        this.date = date;
        this.status = status;
    }
}
