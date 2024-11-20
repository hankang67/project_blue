package com.sparta.projectblue.domain.sse.service;

import com.sparta.projectblue.aop.SlackNotifier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();
    private final SlackNotifier slackNotifier;

    // 구독 관리 (SseEmitter 연결)
    public SseEmitter subscribe(String userId) {
        SseEmitter emitter = new SseEmitter(60 * 1000L); // 1분 타임아웃(연결유지시간)
        emitters.put(userId, emitter); // userId와 emitter -> Map 에 저장하여 구독 관리

        // 처리 성공 및 타임아웃시 -> 만료된 연결 emitter Map 에서 제거
        emitter.onCompletion(() -> emitters.remove(userId));
        emitter.onTimeout(() -> emitters.remove(userId));

        return emitter;
    }

    // 특정 사용자에게 알림을 전송하기 위한 메서드
    public void notify(String userId, String title, String message) {

        // SSE 알림 전송
            SseEmitter emitter = emitters.get(userId);
            if (emitter != null) {
                try {
                    emitter.send(SseEmitter.event()
                            .name("알림") // 이벤트 이름
                            .data(title + "\n" + message)); // 알림 메시지
                } catch (IOException e) {
                    emitters.remove(userId); // 예외 발생시 emitter 정보 제거
                }
            }

            //  Slack 비동기 알림 전송
            CompletableFuture.runAsync(() -> slackNotifier.sendMessage(title, message));
    }
}
