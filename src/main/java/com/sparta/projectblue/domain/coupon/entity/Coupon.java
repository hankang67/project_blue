package com.sparta.projectblue.domain.coupon.entity;

import com.sparta.projectblue.domain.common.entity.BaseEntity;
import com.sparta.projectblue.domain.common.enums.CouponStatus;
import com.sparta.projectblue.domain.common.enums.CouponType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "Coupon")
public class Coupon extends BaseEntity {

    @Column(nullable = false, length = 50)
    private String couponCode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private CouponType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private CouponStatus status;

    @Column(nullable = false, name = "discount_value")
    private int discountValue;

    @Column(nullable = false, name = "start_date")
    private LocalDateTime startDate;

    @Column(nullable = false, name = "end_date")
    private LocalDateTime endDate;

    public Coupon(String couponCode, CouponType type, CouponStatus status,
                  int discountValue, LocalDateTime startDate, LocalDateTime endDate) {
        this.couponCode = couponCode;
        this.type = type;
        this.status = status;
        this.discountValue = discountValue;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
