package com.sparta.projectblue.domain.performance.dto;

import java.time.LocalDateTime;

import com.sparta.projectblue.domain.common.enums.Category;
import com.sparta.projectblue.domain.hall.entity.Hall;
import com.sparta.projectblue.domain.performance.entity.Performance;
import com.sparta.projectblue.domain.poster.entity.Poster;

import lombok.Getter;

@Getter
public class GetPerformanceResponseDto {

    private final String title;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final Long price;
    private final Category category;
    private final String description;
    private final int duration;
    private final String hallName;
    private final String posterName;
    private final String imageUrl;

    public GetPerformanceResponseDto(Performance performance, Hall hall, Poster poster) {

        this.title = performance.getTitle();
        this.startDate = performance.getStartDate();
        this.endDate = performance.getEndDate();
        this.price = performance.getPrice();
        this.category = performance.getCategory();
        this.description = performance.getDescription();
        this.duration = performance.getDuration();
        this.hallName = hall.getName();
        this.posterName = poster.getName();
        this.imageUrl = poster.getImageUrl();
    }
}
