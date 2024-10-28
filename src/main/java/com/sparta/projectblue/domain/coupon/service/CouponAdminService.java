package com.sparta.projectblue.domain.coupon.service;

import com.sparta.projectblue.domain.common.dto.AuthUser;
import com.sparta.projectblue.domain.coupon.dto.CreateCouponRequestDto;
import com.sparta.projectblue.domain.coupon.entity.Coupon;
import com.sparta.projectblue.domain.coupon.repository.CouponRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CouponAdminService {

    private final CouponRepository couponRepository;

    @Transactional
    public Coupon create(AuthUser authUser, @Valid CreateCouponRequestDto requestDto) {

        if (requestDto.getStartDate().isAfter(requestDto.getEndDate())) {
            throw new IllegalArgumentException("쿠폰 유효일이 쿠폰 만료 이후 일 수 없습니다.");
        }

        // 쿠폰 생성
        Coupon coupon =
                new Coupon(
                        requestDto.getCouponCode(),
                        requestDto.getType(),
                        requestDto.getStatus(),
                        requestDto.getMaxQuantity(),
                        0,
                        requestDto.getDiscountValue(),
                        requestDto.getStartDate(),
                        requestDto.getEndDate());

        return couponRepository.save(coupon);
    }

    @Transactional
    public Coupon firstCoupon(AuthUser authUser, Long couponid) {
        Coupon coupon = couponRepository.findByIdOrElseThrow(couponid);

        // 현재 쿠폰 수량 증가
        coupon.incerementQuantity();

        return couponRepository.save(coupon);
    }

    @Transactional
    public void delete(AuthUser authUser, Long id) {

        Coupon coupon = couponRepository.findByIdOrElseThrow(id);

        couponRepository.delete(coupon);
    }
}
