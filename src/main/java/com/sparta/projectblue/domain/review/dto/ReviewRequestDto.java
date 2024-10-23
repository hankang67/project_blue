package com.sparta.projectblue.domain.review.dto;

import com.sparta.projectblue.domain.common.enums.Category;
import com.sparta.projectblue.domain.common.enums.ReservationStatus;
import com.sparta.projectblue.domain.common.enums.ReviewRate;
import com.sparta.projectblue.domain.review.entity.Review;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewRequestDto {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        @NotNull
        private Long reservationId;

        @NotNull
        private ReviewRate reviewRate;

        @NotNull
        private String contents;
    }

    @Getter
    public static class Response {
        private final Long id;
        private final Long performanceId;
        private final Long reservationId;
        private final ReviewRate reviewRate;
        private final String contents;

        // 생성자를 클래스 내부로 이동
        public Response(Review review, Long performanceId) {
            this.id = review.getId();
            this.performanceId = performanceId;
            this.reservationId = review.getReservationId();
            this.reviewRate = review.getReviewRate();
            this.contents = review.getContent();
        }
    }

}

