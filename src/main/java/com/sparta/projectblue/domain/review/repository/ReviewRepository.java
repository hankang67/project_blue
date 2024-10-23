package com.sparta.projectblue.domain.review.repository;

import com.sparta.projectblue.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long>{
    Review findByReservationId(Long reservationId);
}
