package com.sparta.projectblue.domain.round.dto;

import java.time.LocalDateTime;

import com.sparta.projectblue.domain.common.enums.PerformanceStatus;
import com.sparta.projectblue.domain.round.entity.Round;

import lombok.Getter;

@Getter
public class CreateRoundResponseDto {
    private final Long id;
    private final Long performanceId;
    private final LocalDateTime date;
    private final PerformanceStatus status;

    public CreateRoundResponseDto(Round round) {
        this.id = round.getId();
        this.performanceId = round.getPerformanceId();
        this.date = round.getDate();
        this.status = round.getStatus();
    }
}
