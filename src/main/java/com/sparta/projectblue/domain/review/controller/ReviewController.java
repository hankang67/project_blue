package com.sparta.projectblue.domain.review.controller;

import com.sparta.projectblue.config.ApiResponse;
import com.sparta.projectblue.domain.review.dto.ReviewRequestDto;
import com.sparta.projectblue.domain.review.service.ReviewService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
@Tag(name = "Review", description = "관람평 관련 API")
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 생성
    @PostMapping
    public ResponseEntity<ApiResponse<ReviewRequestDto.Response>> createReview(
            @RequestBody ReviewRequestDto.Request requestDto) {
        ReviewRequestDto.Response response = reviewService.createReview(requestDto.getReservationId(), requestDto);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    // 예매 번호로 관람평 조회
    @GetMapping("/{reservationId}")
    public ResponseEntity<ReviewRequestDto.Response> getReviewByReservationId(
            @PathVariable Long reservationId) {
        ReviewRequestDto.Response response = reviewService.getReview(reservationId);
        return ResponseEntity.ok(response);
    }

    // 리뷰 수정
    @PutMapping("/{id}")
    public ResponseEntity<ReviewRequestDto.Response> updateReview(
            @PathVariable Long id,
            @RequestBody ReviewRequestDto.Request requestDto) {
        ReviewRequestDto.Response updatedReview = reviewService.updateReview(id, requestDto);
        return ResponseEntity.ok(updatedReview);
    }

    // 리뷰 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.ok(ApiResponse.successWithNoContent());
    }
}

