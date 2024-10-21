package com.sparta.projectblue.domain.auth.controller;

import com.sparta.projectblue.domain.auth.dto.SignInDto;
import com.sparta.projectblue.domain.auth.dto.SignUpDto;
import com.sparta.projectblue.domain.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth/signup")
    public SignUpDto.Response signup(@Valid @RequestBody SignUpDto.Request signupRequest) {
        return authService.signup(signupRequest);
    }

    @PostMapping("/auth/signin")
    public SignInDto.Response signin(@Valid @RequestBody SignInDto.Request signinRequest) {
        return authService.signin(signinRequest);
    }
}
