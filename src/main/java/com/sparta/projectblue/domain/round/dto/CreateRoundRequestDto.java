package com.sparta.projectblue.domain.round.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateRoundRequestDto {

    private Long performanceId;
    private List<LocalDateTime> dates;
}
