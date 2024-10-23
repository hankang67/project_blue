package com.sparta.projectblue.domain.user.dto;

import com.sparta.projectblue.domain.common.enums.Category;
import com.sparta.projectblue.domain.common.enums.ReservationStatus;
import com.sparta.projectblue.domain.payment.entity.Payment;
import com.sparta.projectblue.domain.performance.entity.Performance;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ReservationDetailDto {

    @Getter
    public static class Response {
        private final Long reservationId;
        private final PerformanceDto performance;
        private PaymentDto payment;
        private final List<Integer> seats;
        private final ReservationStatus status;

        public Response(Long reservationId, PerformanceDto performance, PaymentDto payment, List<Integer> seats, ReservationStatus status) {
            this.reservationId = reservationId;
            this.performance = performance;
            this.payment = payment;
            this.seats = seats;
            this.status = status;
        }
    }

    @Getter
    public static class PerformanceDto {
        private final String title;
        private final Long hallId;
        private final LocalDateTime startDate;
        private final int price;
        private final Category category;
        private final String description;
        private final int duration; // duration in minutes

        public PerformanceDto(Performance performance) {
            this.title = performance.getTitle();
            this.hallId = performance.getHallId();
            this.startDate = performance.getStartDate();
            this.price = performance.getPrice();
            this.category = performance.getCategory();
            this.description = performance.getDescription();
            this.duration = performance.getDuration();
        }
    }

    @Getter
    public static class PaymentDto {
        private final String paymentMethod;
        private final int paymentPrice;
        private final LocalDateTime paymentTime;

        public PaymentDto(Payment payment) {
            this.paymentMethod = payment.getPaymentMethod();
            this.paymentPrice = payment.getPaymentPrice();
            this.paymentTime = payment.getPaymentTime();
        }
    }
}
