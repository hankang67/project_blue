package com.sparta.projectblue.domain.review.controller;

import com.sparta.projectblue.config.ApiResponse;
import com.sparta.projectblue.domain.common.dto.AuthUser;
import com.sparta.projectblue.domain.review.dto.CreateReviewDto;
import com.sparta.projectblue.domain.review.dto.UpdateReviewDto;
import com.sparta.projectblue.domain.review.service.ReviewService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
@Tag(name = "Review", description = "관람평 관련 API")
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 생성
    @PostMapping
    public ResponseEntity<ApiResponse<?>> createReview(
            @AuthenticationPrincipal AuthUser authUser,
            @RequestBody CreateReviewDto.Request request) {
        CreateReviewDto.Response response = reviewService.createReview(authUser.getId(), request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    // 리뷰 수정
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> updateReview(
            @AuthenticationPrincipal AuthUser authUser,
            @PathVariable Long id,
            @RequestBody UpdateReviewDto.Request requestDto) {
        UpdateReviewDto.Response updatedReview = reviewService.updateReview(authUser.getId(), id, requestDto);
        return ResponseEntity.ok(ApiResponse.success(updatedReview));
    }

    // 리뷰 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteReview(
            @AuthenticationPrincipal AuthUser authUser,
            @PathVariable Long id) {
        reviewService.deleteReview(authUser.getId(), id);
        return ResponseEntity.ok(ApiResponse.successWithNoContent());
    }
}

