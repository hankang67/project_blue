package com.sparta.projectblue.domain.performance.dto;

import com.sparta.projectblue.domain.common.enums.Category;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreatePerformanceResponseDto {

    private final Long performanceId;
    private final String title;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final Long price;
    private final Category category;
    private final String description;
    private final int duration;

    public CreatePerformanceResponseDto(Long performanceId,
                                        String title,
                                        LocalDateTime startDate,
                                        LocalDateTime endDate,
                                        Long price,
                                        Category category,
                                        String description,
                                        int duration) {
        this.performanceId = performanceId;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.category = category;
        this.description = description;
        this.duration = duration;
    }
}
