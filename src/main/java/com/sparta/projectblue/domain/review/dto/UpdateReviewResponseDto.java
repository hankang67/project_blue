package com.sparta.projectblue.domain.review.dto;

import com.sparta.projectblue.domain.common.enums.ReviewRate;
import com.sparta.projectblue.domain.review.entity.Review;

import lombok.Getter;

@Getter
public class UpdateReviewResponseDto {

    private final Long id;
    private final Long performanceId;
    private final Long reservationId;
    private final ReviewRate reviewRate;
    private final String contents;

    public UpdateReviewResponseDto(Review review) {

        this.id = review.getId();
        this.performanceId = review.getPerformanceId();
        this.reservationId = review.getReservationId();
        this.reviewRate = review.getReviewRate();
        this.contents = review.getContent();
    }
}
