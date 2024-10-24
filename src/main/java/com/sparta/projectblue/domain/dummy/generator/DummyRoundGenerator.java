package com.sparta.projectblue.domain.dummy.generator;

import com.sparta.projectblue.domain.common.enums.PerformanceStatus;
import com.sparta.projectblue.domain.round.entity.Round;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public class DummyRoundGenerator {
    // 더미 Round 객체를 생성하는 메서드
    public static Round generate(List<Long> performanceIds) {
        // 무작위 Performance ID 선택
        Long performanceId = getRandomPerformanceId(performanceIds);

        // 무작위 날짜 생성 (현재 날짜 이후의 임의의 날짜로 설정)
        LocalDateTime date = getRandomFutureDate();

        // Round 객체 생성 및 반환 (상태는 기본적으로 BEFORE_OPEN)
        return new Round(performanceId, date, PerformanceStatus.BEFORE_OPEN);
    }

    // 무작위 Performance ID 선택 메서드
    private static Long getRandomPerformanceId(List<Long> performanceIds) {
        int index = new Random().nextInt(performanceIds.size());
        return performanceIds.get(index);
    }

    // 무작위 미래 날짜 생성 메서드
    private static LocalDateTime getRandomFutureDate() {
        Random random = new Random();
        int daysToAdd = random.nextInt(365) + 1; // 최대 1년 이내의 무작위 날짜
        int hoursToAdd = random.nextInt(24);
        int minutesToAdd = random.nextInt(60);
        return LocalDateTime.now().plusDays(daysToAdd).plusHours(hoursToAdd).plusMinutes(minutesToAdd);
    }
}
