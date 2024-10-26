package com.sparta.projectblue.domain.round.dto;

import java.time.LocalDateTime;

import com.sparta.projectblue.domain.common.enums.PerformanceStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRoundRequestDto {
    private LocalDateTime date;
    private PerformanceStatus status;
}
