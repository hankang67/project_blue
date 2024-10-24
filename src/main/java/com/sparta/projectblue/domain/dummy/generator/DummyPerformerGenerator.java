package com.sparta.projectblue.domain.dummy.generator;

import com.sparta.projectblue.domain.performer.entity.Performer;

import java.util.UUID;

public class DummyPerformerGenerator {
    // 더미 Performer 객체를 생성하는 메서드
    public static Performer generate() {
        // 무작위 이름, 생년월일, 국가 설정
        String name = "Performer_" + UUID.randomUUID().toString().substring(0, 5);
        String birth = getRandomBirthDate(); // 무작위 생년월일 생성 메서드 호출
        String nation = getRandomNation(); // 무작위 국가 생성 메서드 호출

        // Performer 객체 생성 및 반환
        return new Performer(name, birth, nation);
    }

    // 무작위 생년월일 생성 메서드 (1990년~2000년 사이의 임의의 날짜 생성)
    private static String getRandomBirthDate() {
        int year = 1990 + (int)(Math.random() * 11); // 1990~2000 사이의 연도
        int month = 1 + (int)(Math.random() * 12); // 1~12 사이의 월
        int day = 1 + (int)(Math.random() * 28); // 1~28 사이의 일 (단순화를 위해)
        return String.format("%04d-%02d-%02d", year, month, day);
    }

    // 무작위 국가 생성 메서드
    private static String getRandomNation() {
        String[] nations = {"Korea", "USA", "Japan", "China", "France", "Germany"};
        int index = (int)(Math.random() * nations.length);
        return nations[index];
    }
}
