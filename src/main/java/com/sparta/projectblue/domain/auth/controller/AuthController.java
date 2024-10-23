package com.sparta.projectblue.domain.auth.controller;

import com.sparta.projectblue.domain.auth.dto.SignInDto;
import com.sparta.projectblue.domain.auth.dto.SignUpDto;
import com.sparta.projectblue.domain.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Auth", description = "회원가입 로그인 API")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth/signup")
    @Operation(summary = "회원가입")
    public SignUpDto.Response signup(@Valid @RequestBody SignUpDto.Request signupRequest) {
        return authService.signup(signupRequest);
    }

    @PostMapping("/auth/signin")
    @Operation(summary = "로그인", description = "testuser password : abc123?!")
    public SignInDto.Response signin(@Valid @RequestBody SignInDto.Request signinRequest) {
        return authService.signin(signinRequest);
    }
}
