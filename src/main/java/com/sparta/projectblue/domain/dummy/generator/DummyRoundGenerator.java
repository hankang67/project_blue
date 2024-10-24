package com.sparta.projectblue.domain.dummy.generator;

import com.sparta.projectblue.domain.common.enums.PerformanceStatus;
import com.sparta.projectblue.domain.round.entity.Round;

import java.time.LocalDateTime;
import java.util.List;

public class DummyRoundGenerator {
    private static int performanceIndex = 0; // 순차적으로 접근할 인덱스

    // 더미 Round 객체를 생성하는 메서드
    public static Round generate(List<Long> performanceIds) {
        // 순차적으로 Performance ID 선택
        Long performanceId = getSequentialPerformanceId(performanceIds);

        // 무작위 날짜 생성 (현재 날짜 이후의 임의의 날짜로 설정)
        LocalDateTime date = getRandomFutureDate();

        // Round 객체 생성 및 반환 (상태는 기본적으로 BEFORE_OPEN)
        return new Round(performanceId, date, PerformanceStatus.BEFORE_OPEN);
    }

    // 순차적으로 Performance ID 선택 메서드
    private static Long getSequentialPerformanceId(List<Long> performanceIds) {
        Long performanceId = performanceIds.get(performanceIndex % performanceIds.size());
        performanceIndex++; // 인덱스를 증가시켜 다음 ID로 이동
        return performanceId;
    }

    // 무작위 미래 날짜 생성 메서드
    private static LocalDateTime getRandomFutureDate() {
        int daysToAdd = (int) (Math.random() * 365) + 1; // 최대 1년 이내의 무작위 날짜
        int hoursToAdd = (int) (Math.random() * 24);
        int minutesToAdd = (int) (Math.random() * 60);
        return LocalDateTime.now().plusDays(daysToAdd).plusHours(hoursToAdd).plusMinutes(minutesToAdd);
    }
}
