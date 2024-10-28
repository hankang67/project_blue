package com.sparta.projectblue.domain.coupon.service;

import com.sparta.projectblue.domain.common.dto.AuthUser;
import com.sparta.projectblue.domain.common.enums.UserRole;
import com.sparta.projectblue.domain.coupon.dto.CouponRequestDto;
import com.sparta.projectblue.domain.coupon.entity.Coupon;
import com.sparta.projectblue.domain.coupon.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CouponService {

    private final CouponRepository couponRepository;

    @Transactional
    public Coupon create(AuthUser authUser, CouponRequestDto requestDto) {
        hasRole(authUser);

        if (!requestDto.getStartDate().isAfter(requestDto.getEndDate())) {
            throw new IllegalArgumentException("쿠폰 유효일이 쿠폰 만료 이후 일 수 없습니다.");
        }

        // 쿠폰 생성
        Coupon coupon = new Coupon(
                requestDto.getCouponCode(),
                requestDto.getType(),
                requestDto.getStatus(),
                requestDto.getDiscountValue(),
                requestDto.getStartDate(),
                requestDto.getEndDate());

        return couponRepository.save(coupon);
    }

    @Transactional
    public void delete(AuthUser authUser, Long id) {
        hasRole(authUser);

        Coupon coupon = couponRepository.findByIdOrElseThrow(id);

        couponRepository.delete(coupon);
    }

    // 권한 확인
    public void hasRole(AuthUser authUser) {
        if (!authUser.hasRole(UserRole.ROLE_ADMIN)) {
            throw new IllegalArgumentException("관리자만 접근할 수 있습니다.");
        }
    }
}
