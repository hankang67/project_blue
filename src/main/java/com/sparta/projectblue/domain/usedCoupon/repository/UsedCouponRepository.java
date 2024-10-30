package com.sparta.projectblue.domain.usedCoupon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sparta.projectblue.domain.usedCoupon.entity.UsedCoupon;

public interface UsedCouponRepository extends JpaRepository<UsedCoupon, Long> {
    boolean existsByCouponIdAndUserId(Long couponId, Long userId);
}
