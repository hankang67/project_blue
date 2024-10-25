package com.sparta.projectblue.domain.performance.entity;

import com.sparta.projectblue.domain.common.entity.BaseEntity;
import com.sparta.projectblue.domain.common.enums.Category;
import com.sparta.projectblue.domain.performance.dto.PerformanceUpdateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "performances")
public class Performance extends BaseEntity {

    @Column(nullable = false, name = "hall_id")
    private Long hallId;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, name = "start_date")
    private LocalDateTime startDate;

    @Column(nullable = false, name = "end_date")
    private LocalDateTime endDate;

    @Column(nullable = false)
    private Long price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Category category;

    @Column(nullable = false, length = 255)
    private String description;

    @Column(nullable = false)
    private int duration; // 러닝타임 분단위

    public Performance(Long hallId, String title, LocalDateTime startDate, LocalDateTime endDate, Long price, Category category, String description, int duration) {
        this.hallId = hallId;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.category = category;
        this.description = description;
        this.duration = duration;
    }

    public void update(PerformanceUpdateRequestDto requestDto) {
        this.hallId = requestDto.getHallId();
        this.title = requestDto.getTitle();
        this.startDate = LocalDate.parse(requestDto.getStartDate()).atStartOfDay();
        this.endDate = LocalDate.parse(requestDto.getEndDate()).atTime(LocalTime.MAX);
        this.price = requestDto.getPrice();
        this.category = requestDto.getCategory();
        this.description = requestDto.getDescription();
        this.duration = requestDto.getDuration();
    }
}