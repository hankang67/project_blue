package com.sparta.projectblue.domain.reservation.dto;

import com.sparta.projectblue.domain.common.enums.Category;
import com.sparta.projectblue.domain.common.enums.ReservationStatus;
import com.sparta.projectblue.domain.common.enums.ReviewRate;
import com.sparta.projectblue.domain.payment.entity.Payment;
import com.sparta.projectblue.domain.performance.entity.Performance;
import com.sparta.projectblue.domain.reservation.entity.Reservation;
import com.sparta.projectblue.domain.review.entity.Review;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class GetReservationDto {

    @Getter
    public static class Response {
        private final Category category; // performance
        private final String performanceTitle; // performance

        private final LocalDateTime round; // round

        private final LocalDateTime reservationDate; // reservation
        private final Long reservationId; // reservation
        private final ReservationStatus reservationStatus; // reservation

        private final String userName; // user

        private final List<Integer> seats; // reservedSeat

        private final String paymentMethod; // payment
        private final int paymentPrice; // payment
        private final LocalDateTime paymentTime; // payment

        private final ReviewRate rate ; // review
        private final String content; // review

        public Response(Performance performance, LocalDateTime round, Reservation reservation, String userName, List<Integer> seats, Payment payment, Review review) {
            this.category = performance.getCategory();
            this.performanceTitle = performance.getTitle();

            this.round = round;

            this.reservationDate = reservation.getCreatedAt();
            this.reservationId = reservation.getId();
            this.reservationStatus = reservation.getStatus();

            this.userName = userName;

            this.seats = seats;

            if (payment != null) {
                this.paymentMethod = payment.getPaymentMethod();
                this.paymentPrice = payment.getPaymentPrice();
                this.paymentTime = payment.getPaymentTime();
            } else {
                this.paymentMethod = null;
                this.paymentPrice = 0;
                this.paymentTime = null;
            }

            if (review != null) {
                this.rate = review.getReviewRate();
                this.content = review.getContent();
            } else {
                this.rate = null;
                this.content = null;
            }
        }
    }
}
