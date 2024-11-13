package com.sparta.projectblue.sse;

import com.sparta.projectblue.sse.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisSubscriber {

    private final NotificationService notificationService;

    // Redis 에서 메세지 수신
    public void handleNotificationMessage(String message) {

        // 전달받은 메세지를 클라이언트로 전송
        String[] parts = message.split("::");
        String userId = parts[0];
        String title = parts[1];
        String body = parts[2];

        notificationService.notify(userId, title, body);
    }
}