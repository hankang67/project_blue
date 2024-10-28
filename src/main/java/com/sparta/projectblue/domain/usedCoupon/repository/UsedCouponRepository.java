package com.sparta.projectblue.domain.usedCoupon.repository;

import com.sparta.projectblue.domain.usedCoupon.entity.UsedCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsedCouponRepository extends JpaRepository<UsedCoupon, Long> {
}
