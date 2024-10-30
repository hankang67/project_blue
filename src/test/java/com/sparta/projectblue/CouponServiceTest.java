package com.sparta.projectblue;

import com.sparta.projectblue.domain.common.dto.AuthUser;
import com.sparta.projectblue.domain.coupon.entity.Coupon;
import com.sparta.projectblue.domain.coupon.repository.CouponRepository;
import com.sparta.projectblue.domain.coupon.service.CouponService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CouponServiceTest {

    @Autowired
    private CouponService couponService;

    @Autowired
    private CouponRepository couponRepository;

    @Test
    public void 쿠폰발급_테스트() {

        Long couponId = 1L;

        int threadCount = 10;
        List<CompletableFuture<Void>> futures = new ArrayList<>();

        for (int i = 0; i < threadCount; i++) {
            futures.add(CompletableFuture.runAsync(() -> {
                couponService.firstCoupon(new AuthUser(), couponId);
            }));
        }
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        Coupon coupon = couponRepository.findByIdOrElseThrow(couponId);
        assertTrue(coupon.getCurrentQuantity() <= coupon.getMaxQuantity());
    }
}
