package com.sparta.projectblue.domain.coupon.controller;

import com.sparta.projectblue.config.ApiResponse;
import com.sparta.projectblue.domain.common.dto.AuthUser;
import com.sparta.projectblue.domain.coupon.dto.CreateCouponRequestDto;
import com.sparta.projectblue.domain.coupon.service.CouponAdminService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/coupons")
public class CouponAdminController {

    private final CouponAdminService couponAdminService;

    @PostMapping
    @Operation(summary = "쿠폰 등록")
    public ResponseEntity<ApiResponse<?>> create(
            @AuthenticationPrincipal AuthUser authUser,
            @Valid @RequestBody CreateCouponRequestDto requestDto) {

        return ResponseEntity.ok(
                ApiResponse.success(couponAdminService.create(authUser, requestDto)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "쿠폰 삭제")
    public ResponseEntity<ApiResponse<?>> delete(
            @AuthenticationPrincipal AuthUser authUser, @Valid @PathVariable("id") Long id) {

        couponAdminService.delete(authUser, id);

        return ResponseEntity.ok(ApiResponse.successWithNoContent());
    }
}
