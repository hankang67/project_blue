package com.sparta.projectblue.domain.dummy.generator;

import com.sparta.projectblue.domain.common.enums.ReservationStatus;
import com.sparta.projectblue.domain.reservation.entity.Reservation;

import java.util.List;
import java.util.Random;

public class DummyReservationGenerator {

    // 더미 Reservation 객체를 생성하는 메서드
    public static Reservation generate(List<Long> userIds, List<Long> performanceIds, List<Long> roundIds) {
        // 무작위 User ID 선택
        Long userId = getRandomElement(userIds);

        // 무작위 Performance ID 선택
        Long performanceId = getRandomElement(performanceIds);

        // 무작위 Round ID 선택
        Long roundId = getRandomElement(roundIds);

        // 무작위 상태 생성
        ReservationStatus status = getRandomReservationStatus();

        // 무작위 가격 생성 (예: 8000 ~ 140000 사이)
        int price = getRandomPrice();

        // Reservation 객체 생성 및 반환
        return new Reservation(userId, performanceId, roundId, status, price);
    }

    // 무작위 리스트 요소 선택 메서드
    private static <T> T getRandomElement(List<T> list) {
        int index = new Random().nextInt(list.size());
        return list.get(index);
    }

    // 무작위 ReservationStatus 생성 메서드
    private static ReservationStatus getRandomReservationStatus() {
        ReservationStatus[] statuses = ReservationStatus.values();
        int index = new Random().nextInt(statuses.length);
        return statuses[index];
    }

    // 무작위 가격 생성 메서드
    private static int getRandomPrice() {
        return (new Random().nextInt(62000) + 8000);  // 8000 ~ 140000 사이의 무작위 가격
    }
    }

