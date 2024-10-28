package com.sparta.projectblue.domain.common.generator;

import java.util.UUID;

import com.sparta.projectblue.domain.common.enums.UserRole;
import com.sparta.projectblue.domain.user.entity.User;

public class DummyUserGenerator {
    // 더미 User 생성 메서드
    public static User generate() {
        // 무작위 UUID 기반 이메일, 무작위 이름 및 기본 비밀번호 생성
        String email = UUID.randomUUID().toString().substring(0, 8) + "@example.com";
        String name = "User_" + UUID.randomUUID().toString().substring(0, 5);
        String password = "password123"; // 기본 비밀번호 (실제 환경에서는 해시된 값 사용)

        // 기본 사용자 역할 설정
        UserRole userRole = UserRole.ROLE_USER;

        // User 객체를 생성하고 반환
        return new User(email, name, password, userRole);
    }
}
