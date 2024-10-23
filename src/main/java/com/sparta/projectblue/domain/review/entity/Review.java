package com.sparta.projectblue.domain.review.entity;

import com.sparta.projectblue.domain.common.entity.BaseEntity;
import com.sparta.projectblue.domain.common.enums.ReviewRate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
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
        this.content = content;
        this.reviewRate = reviewRate;
    }
}
