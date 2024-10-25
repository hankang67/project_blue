package com.sparta.projectblue.domain.dummy.generator;

import com.sparta.projectblue.domain.common.enums.Category;
import com.sparta.projectblue.domain.performance.entity.Performance;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class DummyPerformanceGenerator {
    private static int hallIndex = 0; // 순차적으로 접근할 인덱스

    // 더미 Performance 객체를 생성하는 메서드
    public static Performance generate(List<Long> hallIds) {
        // 순차적으로 Hall ID 선택
        Long hallId = getSequentialHallId(hallIds);

        // 무작위 공연 제목 생성
        String title = "Performance_" + UUID.randomUUID().toString().substring(0, 8);

        // 무작위 시작 및 종료 날짜 생성
        LocalDateTime startDate = getRandomStartDate();
        LocalDateTime endDate = startDate.plusDays(getRandomDurationDays());

        // 무작위 가격 생성 (예: 10000 ~ 150000 사이)
        int price = getRandomPrice();

        // 무작위 카테고리 생성
        Category category = getRandomCategory();

        // 무작위 설명 생성
        String description = "Description for " + title;

        // 러닝타임 (예: 60 ~ 180분 사이)
        int duration = getRandomDurationMinutes();

        // Performance 객체 생성 및 반환
        return new Performance(hallId, title, startDate, endDate, price, category, description, duration);
    }

    // 순차적으로 Hall ID 선택 메서드
    private static Long getSequentialHallId(List<Long> hallIds) {
        Long hallId = hallIds.get(hallIndex % hallIds.size());
        hallIndex++; // 인덱스를 증가시켜 다음 ID로 이동
        return hallId;
    }

    // 무작위 시작 날짜 생성 메서드
    private static LocalDateTime getRandomStartDate() {
        int daysToAdd = (int) (Math.random() * 30) + 1; // 현재 날짜로부터 최대 30일 이내의 날짜
        return LocalDateTime.now().plusDays(daysToAdd);
    }

    // 무작위 종료 날짜 생성 메서드
    private static int getRandomDurationDays() {
        return (int) (Math.random() * 10) + 1; // 최소 1일 ~ 최대 10일의 공연 기간
    }

    // 무작위 가격 생성 메서드
    private static int getRandomPrice() {
        return (int) (Math.random() * 140000) + 10000; // 10000 ~ 150000 사이의 무작위 가격
    }

    // 무작위 카테고리 생성 메서드
    private static Category getRandomCategory() {
        Category[] categories = Category.values();
        int index = (int) (Math.random() * categories.length);
        return categories[index];
    }

    // 무작위 러닝타임 생성 메서드
    private static int getRandomDurationMinutes() {
        return (int) (Math.random() * 120) + 60; // 60분 ~ 180분 사이의 러닝타임
    }
}
