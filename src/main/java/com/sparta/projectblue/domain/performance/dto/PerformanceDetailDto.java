package com.sparta.projectblue.domain.performance.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PerformanceDetailDto {

    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int price;
    private String category;
    private String description;
    private int duration;
    private String hallName;

    public PerformanceDetailDto(String title, LocalDateTime startDate, LocalDateTime endDate, int price, String category, String description, int duration, String hallName) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.category = category;
        this.description = description;
        this.duration = duration;
        this.hallName = hallName;
    }


}



