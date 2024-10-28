package com.sparta.projectblue.domain.common.generator;

import com.sparta.projectblue.domain.hall.entity.Hall;

public class DummyHallGenerator {
    // 더미 Hall 객체를 생성하는 메서드
    public static Hall generate() {
        // 무작위 데이터 생성
        String name = "Hall_" + (int) (Math.random() * 100);
        String address = "Sample Address " + (int) (Math.random() * 1000);
        int seats = (int) (Math.random() * 500) + 50; // 50 ~ 549 사이의 좌석 수

        // Hall 객체 생성 및 반환
        return new Hall(name, address, seats);
    }
}
