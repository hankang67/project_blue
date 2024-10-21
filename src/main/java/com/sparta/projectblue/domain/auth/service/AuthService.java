package com.sparta.projectblue.domain.auth.service;

import com.sparta.projectblue.config.JwtUtil;
import com.sparta.projectblue.domain.auth.dto.SignInDto;
import com.sparta.projectblue.domain.auth.dto.SignUpDto;
import com.sparta.projectblue.domain.common.enums.UserRole;
import com.sparta.projectblue.domain.common.exception.AuthException;
import com.sparta.projectblue.domain.user.entity.User;
import com.sparta.projectblue.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public SignUpDto.Response signup(SignUpDto.Request signupRequest) {
        validatePassword(signupRequest.getPassword());

        if (userRepository.existsByEmailAndIsDeletedTrue(signupRequest.getEmail())) {
            throw new IllegalArgumentException("탈퇴한 유저의 이메일은 재사용할 수 없습니다.");
        }

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        String encodedPassword = passwordEncoder.encode(signupRequest.getPassword());
        UserRole userRole = UserRole.of(signupRequest.getUserRole());

        User newUser =
                new User(
                        signupRequest.getEmail(),
                        signupRequest.getName(),
                        encodedPassword,
                        userRole);
        User savedUser = userRepository.save(newUser);

        String bearerToken =
                jwtUtil.createToken(
                        savedUser.getId(), savedUser.getEmail(), savedUser.getName(), userRole);

        return new SignUpDto.Response(bearerToken);
    }

    public SignInDto.Response signin(SignInDto.Request signinRequest) {
        User user =
                userRepository
                        .findByEmail(signinRequest.getEmail())
                        .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 유저입니다."));

        // 로그인 시 이메일과 비밀번호가 일치하지 않을 경우 401을 반환합니다.
        if (!passwordEncoder.matches(signinRequest.getPassword(), user.getPassword())) {
            throw new AuthException("잘못된 비밀번호입니다.");
        }

        String bearerToken =
                jwtUtil.createToken(
                        user.getId(), user.getEmail(), user.getName(), user.getUserRole());

        return new SignInDto.Response(bearerToken);
    }

    private void validatePassword(String password) {
        if (password.length() < 8) {
            throw new IllegalArgumentException("비밀번호는 최소 8자 이상이어야 합니다.");
        }
        if (!password.matches(".*[a-zA-Z].*")) { // 대문자 또는 소문자 포함
            throw new IllegalArgumentException("비밀번호는 대문자 또는 소문자를 포함해야 합니다.");
        }
        if (!password.matches(".*\\d.*")) {
            throw new IllegalArgumentException("비밀번호는 숫자를 포함해야 합니다.");
        }
        if (!password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
            throw new IllegalArgumentException("비밀번호는 특수문자를 포함해야 합니다.");
        }
    }
}

