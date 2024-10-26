package com.sparta.projectblue.domain.review.entity;

import jakarta.persistence.*;

import com.sparta.projectblue.domain.common.entity.BaseEntity;
import com.sparta.projectblue.domain.common.enums.ReviewRate;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "reviews")
public class Review extends BaseEntity {

    @Column(nullable = false, name = "performance_id")
    private Long performanceId;

    @Column(nullable = false, name = "reservation_id")
    private Long reservationId;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, length = 5, name = "review_rate")
    private ReviewRate reviewRate;

    @Column(nullable = false, length = 255)
    private String content;

    public Review(Long performanceId, Long reservationId, ReviewRate reviewRate, String content) {
        this.performanceId = performanceId;
        this.reservationId = reservationId;
        this.reviewRate = reviewRate;
        this.content = content;
    }

    public void updateReview(ReviewRate reviewRate, String content) {
        this.reviewRate = reviewRate;
        this.content = content;
    }
}
