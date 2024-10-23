package com.sparta.projectblue.domain.review.controller;

import com.sparta.projectblue.config.ApiResponse;
import com.sparta.projectblue.domain.common.dto.AuthUser;
import com.sparta.projectblue.domain.review.dto.CreateReviewDto;
import com.sparta.projectblue.domain.review.dto.UpdateReviewDto;
import com.sparta.projectblue.domain.review.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "관람평 작성", description = "예매한 공연에 관람평 작성 가능")
    public ResponseEntity<ApiResponse<?>> create(
            @AuthenticationPrincipal AuthUser authUser,
            @RequestBody CreateReviewDto.Request request) {
        CreateReviewDto.Response response = reviewService.create(authUser.getId(), request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    // 리뷰 수정
    @PutMapping("/{id}")
    @Operation(summary = "관람평 수정")
    public ResponseEntity<ApiResponse<?>> update(
            @AuthenticationPrincipal AuthUser authUser,
            @PathVariable Long id,
            @RequestBody UpdateReviewDto.Request requestDto) {
        UpdateReviewDto.Response updatedReview = reviewService.update(authUser.getId(), id, requestDto);
        return ResponseEntity.ok(ApiResponse.success(updatedReview));
    }

    // 리뷰 삭제
    @DeleteMapping("/{id}")
    @Operation(summary = "관람평 삭제")
    public ResponseEntity<ApiResponse<?>> delete(
            @AuthenticationPrincipal AuthUser authUser,
            @PathVariable Long id) {
        reviewService.delete(authUser.getId(), id);
        return ResponseEntity.ok(ApiResponse.successWithNoContent());
    }
}

