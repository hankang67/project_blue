package com.sparta.projectblue.domain.review.service;

import com.sparta.projectblue.domain.performance.repository.PerformanceRepository;
import com.sparta.projectblue.domain.reservation.entity.Reservation;
import com.sparta.projectblue.domain.reservation.repository.ReservationRepository;
import com.sparta.projectblue.domain.review.dto.ReviewRequestDto;
import com.sparta.projectblue.domain.review.entity.Review;
import com.sparta.projectblue.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final PerformanceRepository performanceRepository;
    private final ReservationRepository reservationRepository;

    // 리뷰 생성
    @Transactional
    public ReviewRequestDto.Response createReview(Long id, ReviewRequestDto.Request request) {
        Reservation reservation = reservationRepository.findById(request.getReservationId())
                .orElseThrow(() -> new IllegalArgumentException("해당 예매를 찾을 수 없습니다."));

        // performanceId는 reservation에서 가져옴
        Review review = new Review(reservation.getPerformanceId(), reservation.getId(), request.getReviewRate(), request.getContents());
        reviewRepository.save(review);
        return new ReviewRequestDto.Response(review, reservation.getPerformanceId());
    }

    // 예매 번호로 리뷰 조회
    public ReviewRequestDto.Response getReview(Long reservationId) {

        Review review = reviewRepository.findById(reservationId)
//                    .orElseThrow(() -> new IllegalArgumentException("Payment not found for reservation"));
                .orElse(null);

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("해당 예매를 찾을 수 없습니다"));

        return new ReviewRequestDto.Response(review, reservation.getPerformanceId());
    }

    @Transactional
    public ReviewRequestDto.Response updateReview(Long id, ReviewRequestDto.Request request) {
        Reservation reservation = reservationRepository.findById(request.getReservationId())
                .orElseThrow(() -> new IllegalArgumentException("해당 예매를 찾을 수 없습니다."));

        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰를 찾을 수 없습니다."));

        review.updateReview(request.getReviewRate(), request.getContents());
        reviewRepository.save(review);

        return new ReviewRequestDto.Response(review, reservation.getPerformanceId());
    }

    @Transactional
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}
