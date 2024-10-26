package com.sparta.projectblue.domain.review.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sparta.projectblue.domain.review.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByPerformanceId(Long performanceId);

    Optional<Review> findByReservationId(Long id);

    boolean existsByReservationIdAndPerformanceId(Long reservationId, Long performanceId);
}
