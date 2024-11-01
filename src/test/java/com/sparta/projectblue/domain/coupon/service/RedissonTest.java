package com.sparta.projectblue.domain.coupon.service;

import com.sparta.projectblue.domain.common.dto.AuthUser;
import com.sparta.projectblue.domain.common.enums.CouponStatus;
import com.sparta.projectblue.domain.common.enums.CouponType;
import com.sparta.projectblue.domain.common.enums.UserRole;
import com.sparta.projectblue.domain.coupon.entity.Coupon;
import com.sparta.projectblue.domain.coupon.repository.CouponRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RedissonTest {

    @Autowired private CouponService couponService;

    @Autowired private CouponRepository couponRepository;

    private Long couponId;

    @BeforeEach
    public void setUp() {
        // 쿠폰 생성 및 저장
        Coupon coupon =
                new Coupon(
                        "쿠폰코드",
                        CouponType.AMOUNT,
                        CouponStatus.ACTIVE,
                        5000,
                        0,
                        2000L,
                        LocalDateTime.now(),
                        LocalDateTime.now().plusDays(3));
        Coupon savedCoupon = couponRepository.save(coupon);
        couponId = savedCoupon.getId();
        System.out.println("쿠폰Id: " + couponId);
    }

    @Test
    public void 쿠폰발급_테스트() throws InterruptedException {
        int testCount = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CountDownLatch countDownLatch = new CountDownLatch(testCount);
        AtomicInteger atomicInteger = new AtomicInteger(0);
        AtomicInteger atomicexception = new AtomicInteger(0);

        long startTime = System.currentTimeMillis(); // 테스트 시작시간

        for (int i = 0; i < testCount; i++) {
            executorService.submit(
                    () -> {
                        try {
                            AuthUser authUser =
                                    new AuthUser(1l, "test@test.com", "name", UserRole.ROLE_USER);
                            couponService.firstCoupon(authUser, couponId); // 쿠폰 발급
                            atomicInteger.incrementAndGet(); // 0부터 쿠폰 발급 카운트 증가
                        } catch (Exception e) {
                            atomicexception.incrementAndGet();
                            System.out.println("예외발생" + e.getMessage());
                        } finally {
                            countDownLatch.countDown(); // 작업 완료시 카운트 감소
                        }
                    });
        }

        countDownLatch.await(); // 모든 쓰레드 작업 완료 대기
        executorService.shutdown(); // 쓰레드 풀 종료

        long endTime = System.currentTimeMillis(); // 테스트 종료 시간
        long durationInMillis = endTime - startTime; // 테스트 시간 계간
        double durationInSeconds = durationInMillis / 1000.0;

        Coupon coupon = couponRepository.findByIdOrElseThrow(couponId);

        assertNotEquals(
                atomicInteger.get(),
                coupon.getCurrentQuantity(),
                "성공한 발급 수량과 최종 발급 수량이 일치하지 않습니다.");
        assertTrue(
                coupon.getCurrentQuantity() <= coupon.getMaxQuantity(),
                "쿠폰이 전부 소진되었습니다."
                        + coupon.getCurrentQuantity()
                        + "/최대수량"
                        + coupon.getMaxQuantity());

        System.out.println("최종 발급된 쿠폰 수량: " + coupon.getCurrentQuantity());
        System.out.println("성공한 발급 수량: " + atomicInteger.get());
        System.out.println("예외 발생" + atomicexception.get());
        System.out.println("테스트 실행 시간: " + durationInSeconds + "초");
    }

    @Test
    public void 쿠폰발급_낙관적락_테스트() throws InterruptedException {
        int testCount = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CountDownLatch countDownLatch = new CountDownLatch(testCount);
        AtomicInteger atomicInteger = new AtomicInteger(0);
        AtomicInteger atomicexception = new AtomicInteger(0);

        long startTime = System.currentTimeMillis(); // 테스트 시작시간

        for (int i = 0; i < testCount; i++) {
            executorService.submit(
                    () -> {
                        try {
                            AuthUser authUser =
                                    new AuthUser(1l, "test@test.com", "name", UserRole.ROLE_USER);
                            couponService.firstCoupon(authUser, couponId); // 쿠폰 발급
                            atomicInteger.incrementAndGet(); // 0부터 쿠폰 발급 카운트 증가
                        } catch (OptimisticLockingFailureException e) {
                            atomicexception.incrementAndGet();
                            System.out.println("예외발생" + e.getMessage());
                        } finally {
                            countDownLatch.countDown(); // 작업 완료시 카운트 감소
                        }
                    });
        }

        countDownLatch.await(); // 모든 쓰레드 작업 완료 대기
        executorService.shutdown(); // 쓰레드 풀 종료

        long endTime = System.currentTimeMillis(); // 테스트 종료 시간
        long durationInMillis = endTime - startTime; // 테스트 시간 계간
        double durationInSeconds = durationInMillis / 1000.0;

        Coupon coupon = couponRepository.findByIdOrElseThrow(couponId);

        assertNotEquals(
                atomicInteger.get(),
                coupon.getCurrentQuantity(),
                "성공한 발급 수량과 최종 발급 수량이 일치하지 않습니다.");
        assertTrue(
                coupon.getCurrentQuantity() <= coupon.getMaxQuantity(),
                "쿠폰이 전부 소진되었습니다."
                        + coupon.getCurrentQuantity()
                        + "/최대수량"
                        + coupon.getMaxQuantity());

        System.out.println("최종 발급된 쿠폰 수량: " + coupon.getCurrentQuantity());
        System.out.println("성공한 발급 수량: " + atomicInteger.get());
        System.out.println("낙관적 락 예외 발생" + atomicexception.get());
        System.out.println("테스트 실행 시간: " + durationInSeconds + "초");
    }

    @Test
    public void 쿠폰발급_비관적락_테스트() throws InterruptedException {
        int testCount = 1000;
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CountDownLatch countDownLatch = new CountDownLatch(testCount);
        AtomicInteger atomicInteger = new AtomicInteger(0);
        AtomicInteger atomicexception = new AtomicInteger(0);

        long startTime = System.currentTimeMillis(); // 테스트 시작시간

        AuthUser authUser = new AuthUser(1L, "test@test.com", "name", UserRole.ROLE_USER);

        for (int i = 0; i < testCount; i++) {
            executorService.submit(
                    () -> {
                        try {
                            couponService.firstCoupon(authUser, couponId); // 쿠폰 발급
                            atomicInteger.incrementAndGet(); // 0부터 쿠폰 발급 카운트 증가
                        } catch (Exception e) {
                            atomicexception.incrementAndGet();
                            System.out.println("예외발생" + e.getMessage());
                        } finally {
                            countDownLatch.countDown(); // 작업 완료시 카운트 감소
                        }
                    });
        }

        countDownLatch.await(); // 모든 쓰레드 작업 완료 대기
        executorService.shutdown(); // 쓰레드 풀 종료

        long endTime = System.currentTimeMillis(); // 테스트 종료 시간
        long durationInMillis = endTime - startTime; // 테스트 시간 계간
        double durationInSeconds = durationInMillis / 1000.0;

        //        Coupon coupon = couponService.firstCoupon(authUser, couponId);
        Coupon coupon = couponRepository.findByIdOrElseThrow(couponId);

        assertEquals(
                atomicInteger.get(), coupon.getCurrentQuantity(), "성공한 발급 수량과 최종 발급 수량이 일치합니다.");
        assertTrue(
                coupon.getCurrentQuantity() <= coupon.getMaxQuantity(),
                "쿠폰이 전부 소진되었습니다."
                        + coupon.getCurrentQuantity()
                        + "/최대수량"
                        + coupon.getMaxQuantity());

        System.out.println("최종 발급된 쿠폰 수량: " + coupon.getCurrentQuantity());
        System.out.println("성공한 발급 수량: " + atomicInteger.get());
        System.out.println("예외 발생" + atomicexception.get());
        System.out.println("테스트 실행 시간: " + durationInSeconds + "초");
    }

    @Test
    public void 쿠폰발급_분산락_테스트() throws InterruptedException {
        int testCount = 1000;
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CountDownLatch countDownLatch = new CountDownLatch(testCount);
        AtomicInteger atomicInteger = new AtomicInteger(0);
        AtomicInteger atomicexception = new AtomicInteger(0);

        long startTime = System.currentTimeMillis(); // 테스트 시작시간

        AuthUser authUser = new AuthUser(1L, "test@test.com", "name", UserRole.ROLE_USER);

        for (int i = 0; i < testCount; i++) {
            executorService.submit(
                    () -> {
                        try {
                            couponService.firstCoupon(authUser, couponId); // 쿠폰 발급
                            atomicInteger.incrementAndGet(); // 0부터 쿠폰 발급 카운트 증가
                        } catch (Exception e) {
                            atomicexception.incrementAndGet();
                            System.out.println("예외발생" + e.getMessage());
                        } finally {
                            countDownLatch.countDown(); // 작업 완료시 카운트 감소
                        }
                    });
        }

        countDownLatch.await(); // 모든 쓰레드 작업 완료 대기
        executorService.shutdown(); // 쓰레드 풀 종료

        long endTime = System.currentTimeMillis(); // 테스트 종료 시간
        long durationInMillis = endTime - startTime; // 테스트 시간 계간
        double durationInSeconds = durationInMillis / 1000.0;

        Coupon coupon = couponRepository.findByIdOrElseThrow(couponId);

        assertEquals(
                atomicInteger.get(), coupon.getCurrentQuantity(), "성공한 발급 수량과 최종 발급 수량이 일치합니다.");
        assertTrue(
                coupon.getCurrentQuantity() <= coupon.getMaxQuantity(),
                "쿠폰이 전부 소진되었습니다."
                        + coupon.getCurrentQuantity()
                        + "/최대수량"
                        + coupon.getMaxQuantity());

        System.out.println("최종 발급된 쿠폰 수량: " + coupon.getCurrentQuantity());
        System.out.println("성공한 발급 수량: " + atomicInteger.get());
        System.out.println("예외 발생" + atomicexception.get());
        System.out.println("테스트 실행 시간: " + durationInSeconds + "초");
    }
}
