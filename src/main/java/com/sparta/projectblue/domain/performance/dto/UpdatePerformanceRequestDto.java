package com.sparta.projectblue.domain.performance.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

import com.sparta.projectblue.domain.common.enums.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePerformanceRequestDto {

    @NotNull private String title;
    @NotNull private String startDate;
    @NotNull private String endDate;
    @NotNull private Long price;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Category category;

    @NotNull private String description;
    @NotNull private Long hallId;
    @NotNull private int duration;
    @NotNull private int seats;

    private Long[] performers;
}
