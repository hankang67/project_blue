package com.sparta.projectblue.domain.review.service;

import com.sparta.projectblue.domain.common.enums.ReservationStatus;
import com.sparta.projectblue.domain.reservation.entity.Reservation;
import com.sparta.projectblue.domain.reservation.repository.ReservationRepository;
import com.sparta.projectblue.domain.review.dto.CreateReviewDto;
import com.sparta.projectblue.domain.review.dto.UpdateReviewDto;
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

    private final ReservationRepository reservationRepository;

    // 리뷰 생성
    @Transactional
    public CreateReviewDto.Response create(Long userId, CreateReviewDto.Request request) {
        Reservation reservation = reservationRepository.findById(request.getReservationId())
                .orElseThrow(() -> new IllegalArgumentException("해당 예매를 찾을 수 없습니다."));

//        if (!reservation.getUserId().equals(userId)) {
//            throw new IllegalArgumentException("해당 예매의 사용자가 아닙니다.");
//        }

        // 예매 상태가 COMPLETED 일 때만 리뷰를 등록할 수 있음
        if (!reservation.getStatus().equals(ReservationStatus.COMPLETED)) {
            throw new IllegalArgumentException("결제가 완료된 공연에 대해서만 리뷰를 작성할 수 있습니다.");
        }

        // 유저가 이미 한번 리뷰를 달았는지 검증
        boolean reviewExists = reviewRepository.existsByReservationIdAndPerformanceId(
                request.getReservationId(), reservation.getPerformanceId());
        if (reviewExists) {
            throw new IllegalArgumentException("이 공연에 대한 리뷰는 이미 등록되었습니다.");
        }


        Review review = new Review(reservation.getPerformanceId(), reservation.getId(), request.getReviewRate(), request.getContents());

        reviewRepository.save(review);

        return new CreateReviewDto.Response(review);
    }

    @Transactional
    public UpdateReviewDto.Response update(Long userId, Long id, UpdateReviewDto.Request request) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰를 찾을 수 없습니다."));

        Reservation reservation = reservationRepository.findById(review.getReservationId())
                .orElseThrow(() -> new IllegalArgumentException("해당 예매를 찾을 수 없습니다."));

        if (!reservation.getUserId().equals(userId)) {
            throw new IllegalArgumentException("리뷰 작성자가 아닙니다");
        }

        review.updateReview(request.getReviewRate(), request.getContents());

        return new UpdateReviewDto.Response(review);
    }

    @Transactional
    public void delete(Long userId, Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰를 찾을 수 없습니다."));

        Reservation reservation = reservationRepository.findById(review.getReservationId())
                .orElseThrow(() -> new IllegalArgumentException("해당 예매를 찾을 수 없습니다."));

        if (!reservation.getUserId().equals(userId)) {
            throw new IllegalArgumentException("리뷰 작성자가 아닙니다");
        }

        reviewRepository.delete(review);
    }
}
