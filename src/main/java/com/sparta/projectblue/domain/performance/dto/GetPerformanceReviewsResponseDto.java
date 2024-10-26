package com.sparta.projectblue.domain.performance.dto;

import com.sparta.projectblue.domain.common.enums.ReviewRate;
import lombok.Getter;

import java.util.List;

@Getter
public class GetPerformanceReviewsResponseDto {

    private final List<ReviewInfo> reviews;

    public GetPerformanceReviewsResponseDto(List<ReviewInfo> reviews) {
        this.reviews = reviews;
    }


    @Getter
    public static class ReviewInfo {
        private final ReviewRate rate;
        private final String content;

        public ReviewInfo(ReviewRate rate, String content) {
            this.rate = rate;
            this.content = content;
        }
    }
}
