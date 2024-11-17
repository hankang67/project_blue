package com.sparta.projectblue.domain.usedcoupon.repository;

import com.sparta.projectblue.domain.usedcoupon.entity.UsedCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsedCouponRepository extends JpaRepository<UsedCoupon, Long> {
    boolean existsByCouponIdAndUserId(Long couponId, Long userId);
}
