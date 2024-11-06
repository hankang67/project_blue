package com.sparta.projectblue.domain.usedCoupon.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import com.sparta.projectblue.domain.common.entity.BaseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "used_coupon")
public class UsedCoupon extends BaseEntity {

    @Column(nullable = false, name = "coupon_id")
    private Long couponId;

    @Column(nullable = false, name = "user_id")
    private Long userId;

    @Column(nullable = false, name = "reservation_id")
    private Long reservationId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false, name = "used_at")
    private LocalDateTime usedAt;

    @Column(nullable = false, name = "discount_amount")
    private Long discountAmount;

    public UsedCoupon(
            Long couponId,
            Long userId,
            Long reservationId,
            LocalDateTime usedAt,
            Long discountAmount) {

        this.couponId = couponId;
        this.userId = userId;
        this.reservationId = reservationId;
        this.usedAt = usedAt;
        this.discountAmount = discountAmount;
    }
}
