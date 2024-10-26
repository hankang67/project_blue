package com.sparta.projectblue.domain.round.dto;

import com.sparta.projectblue.domain.common.enums.PerformanceStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRoundRequestDto {
    private LocalDateTime date;
    private PerformanceStatus status;
}
