package com.sparta.projectblue.domain.round.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateRoundRequestDto {
    private Long performanceId;
    private List<LocalDateTime> dates;
}
