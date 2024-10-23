package com.sparta.projectblue.domain.performance.dto;

import com.sparta.projectblue.domain.common.enums.Category;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class PerformanceUpdateRequestDto {
    @NotNull
    private String title;
    @NotNull
    private String startDate;
    @NotNull
    private String endDate;
    @NotNull
    private int price;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Category category;
    @NotNull
    private String description;
    @NotNull
    private Long hallId;
    @NotNull
    private int duration;
    @NotNull
    private int seats;

    private Long[] performers;
}
