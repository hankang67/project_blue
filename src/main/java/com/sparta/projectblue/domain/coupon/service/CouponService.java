package com.sparta.projectblue.domain.coupon.service;

import com.sparta.projectblue.domain.common.dto.AuthUser;
import com.sparta.projectblue.domain.coupon.dto.GetCouponResponseDto;
import com.sparta.projectblue.domain.coupon.entity.Coupon;
import com.sparta.projectblue.domain.coupon.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CouponService {

    private final CouponRepository couponRepository;

    public GetCouponResponseDto getCoupon(Long id) {

        Coupon coupon = couponRepository.findByIdOrElseThrow(id);

        return new GetCouponResponseDto(coupon);
    }

    public Page<GetCouponResponseDto> getUserCoupon(AuthUser authUser, int page, int size) {

        Pageable pageable = PageRequest.of(page - 1, size);

        return couponRepository.findAll(pageable).map(GetCouponResponseDto::new);
    }
}
