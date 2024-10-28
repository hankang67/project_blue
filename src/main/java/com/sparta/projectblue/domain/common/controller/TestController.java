package com.sparta.projectblue.domain.common.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.projectblue.domain.common.service.TestService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Test", description = "테스트 데이터 생성용 API")
public class TestController {

    private final TestService testService;

    @GetMapping
    @Operation(summary = "테스트 데이터 생성", description = "그냥 호출하면 테스트 데이터가 생성됩니다")
    public void test() {
        testService.test();
    }
}
