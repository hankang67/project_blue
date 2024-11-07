package com.sparta.projectblue.domain.usedCoupon.entity;

import com.sparta.projectblue.domain.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "used_coupon")
public class UsedCoupon extends BaseEntity {

    @Column(nullable = false, name = "coupon_id")
    private Long couponId;

    @Column(nullable = false, name = "user_id")
    private Long userId;

    @Column(name = "reservation_id")
    private Long reservationId;

    @Column(nullable = false, name = "used_at")
    private LocalDateTime usedAt;

    @Column(nullable = false, name = "discount_amount")
    private Long discountAmount;

    @Column(nullable = false, name = "issued_at")
    private LocalDateTime issuedAt;

    public UsedCoupon(
            Long couponId,
            Long userId,
            Long reservationId,
            LocalDateTime usedAt,
            Long discountAmount,
            LocalDateTime issuedAt) {

        this.couponId = couponId;
        this.userId = userId;
        this.reservationId = reservationId;
        this.usedAt = usedAt;
        this.discountAmount = discountAmount;
        this.issuedAt = issuedAt;
    }
}
