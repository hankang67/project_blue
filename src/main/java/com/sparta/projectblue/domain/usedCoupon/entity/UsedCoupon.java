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

    @Column(nullable = false, name = "payment_id")
    private Long paymentId;

    @Column(nullable = false, name = "reservation_id")
    private Long reservationId;

    @Column(nullable = false, name = "used_at")
    private LocalDateTime usedAt;

    @Column(nullable = false, name = "discount_amount")
    private int discountAmount;

    public UsedCoupon(Long couponId, Long userId, Long paymentId, Long reservationId, LocalDateTime usedAt,
                      int discountAmount) {
        this.couponId = couponId;
        this.userId = userId;
        this.paymentId = paymentId;
        this.reservationId = reservationId;
        this.usedAt = usedAt;
        this.discountAmount = discountAmount;
    }


}
