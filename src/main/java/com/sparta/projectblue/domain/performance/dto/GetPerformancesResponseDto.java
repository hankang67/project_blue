package com.sparta.projectblue.domain.performance.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
// @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class GetPerformancesResponseDto {

    private String title;
    private String hallNm;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

}


