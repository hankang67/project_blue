package com.sparta.projectblue.domain.performance.entity;

import com.sparta.projectblue.domain.common.entity.BaseEntity;
import com.sparta.projectblue.domain.common.enums.Category;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "performances")
public class Performance extends BaseEntity {

    @Column(nullable = false)
    private Long hallId;

    @Column(nullable = false, length = 20)
    private String title;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private Category category;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int duration; // 러닝타임 분단위

    public Performance(Long hallId, String title, LocalDateTime startDate, LocalDateTime endDate, int price, Category category, String description, int duration) {
        this.hallId = hallId;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.category = category;
        this.description = description;
        this.duration = duration;
    }
}