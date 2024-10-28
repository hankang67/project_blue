package com.sparta.projectblue.domain.coupon.controller;

import com.sparta.projectblue.config.ApiResponse;
import com.sparta.projectblue.domain.common.dto.AuthUser;
import com.sparta.projectblue.domain.coupon.dto.CouponRequestDto;
import com.sparta.projectblue.domain.coupon.entity.Coupon;
import com.sparta.projectblue.domain.coupon.service.CouponService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/coupons")
public class CouponController {

    private final CouponService couponService;

    @PostMapping
    @Operation(summary = "쿠폰 등록")
    public ResponseEntity<ApiResponse<Coupon>> create(
            @AuthenticationPrincipal AuthUser authUser,
            @Valid @RequestBody CouponRequestDto requestDto) {

        return ResponseEntity.ok(ApiResponse.success(couponService.create(authUser, requestDto)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "쿠폰 삭제")
    public ResponseEntity<ApiResponse<?>> delete(
            @AuthenticationPrincipal AuthUser authUser,
            @Valid @PathVariable("id") Long id) {

        couponService.delete(authUser, id);

        return ResponseEntity.ok(ApiResponse.successWithNoContent());
    }
}
