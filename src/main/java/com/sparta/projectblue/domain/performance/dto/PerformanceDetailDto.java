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
    private Long price;
    private String category;
    private String description;
    private int duration;
    private String hallName;
    private String posterName;
    private String posterUrl;

    @QueryProjection
    public PerformanceDetailDto(String title, LocalDateTime startDate, LocalDateTime endDate, Long price, String category, String description, int duration, String hallName,String posterName, String posterUrl) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.category = category;
        this.description = description;
        this.duration = duration;
        this.hallName = hallName;
        this.posterName = posterName;
        this.posterUrl = posterUrl;
    }
}



