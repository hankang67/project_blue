package com.sparta.projectblue.domain.dummy.generator;

import com.sparta.projectblue.domain.poster.entity.Poster;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class DummyPosterGenerator {
    // 더미 Poster 객체를 생성하는 메서드
    public static Poster generate(List<Long> performanceIds) {
        // 무작위 Performance ID 선택
        Long performanceId = getRandomPerformanceId(performanceIds);

        // 무작위 포스터 이름 생성
        String name = "Poster_" + UUID.randomUUID().toString().substring(0, 5);

        // 무작위 이미지 URL 생성
        String imageUrl = getRandomImageUrl();

        // Poster 객체 생성 및 반환
        return new Poster(performanceId, name, imageUrl);
    }

    // 무작위 Performance ID 선택 메서드
    private static Long getRandomPerformanceId(List<Long> performanceIds) {
        int index = new Random().nextInt(performanceIds.size());
        return performanceIds.get(index);
    }

    // 무작위 이미지 URL 생성 메서드
    private static String getRandomImageUrl() {
        // 여기에서는 예시로 임의의 이미지 URL을 생성
        String[] sampleUrls = {
                "https://example.com/image1.jpg",
                "https://example.com/image2.jpg",
                "https://example.com/image3.jpg",
                "https://example.com/image4.jpg"
        };
        int index = new Random().nextInt(sampleUrls.length);
        return sampleUrls[index];
    }
}
