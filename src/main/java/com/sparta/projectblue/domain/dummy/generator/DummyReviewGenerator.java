package com.sparta.projectblue.domain.dummy.generator;

import com.sparta.projectblue.domain.common.enums.ReviewRate;
import com.sparta.projectblue.domain.review.entity.Review;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class DummyReviewGenerator {
    // 더미 Review 객체를 생성하는 메서드
    public static Review generate(List<Long> reservationIds, List<Long> performanceIds) {
        // 무작위 Reservation ID 선택
        Long reservationId = getRandomElement(reservationIds);

        // 무작위 Performance ID 선택
        Long performanceId = getRandomElement(performanceIds);

        // 무작위 리뷰 점수 생성
        ReviewRate reviewRate = getRandomReviewRate();

        // 무작위 리뷰 내용 생성
        String content = generateRandomContent();

        // Review 객체 생성 및 반환
        return new Review(performanceId, reservationId, reviewRate, content);
    }

    // 무작위 리스트 요소 선택 메서드
    private static <T> T getRandomElement(List<T> list) {
        int index = new Random().nextInt(list.size());
        return list.get(index);
    }

    // 무작위 ReviewRate 생성 메서드
    private static ReviewRate getRandomReviewRate() {
        ReviewRate[] rates = ReviewRate.values();
        int index = new Random().nextInt(rates.length);
        return rates[index];
    }

    // 무작위 리뷰 내용 생성 메서드
    private static String generateRandomContent() {
        // UUID를 이용하여 무작위로 간단한 리뷰 내용을 생성
        return "Review content " + UUID.randomUUID().toString().substring(0, 10);
    }
}
