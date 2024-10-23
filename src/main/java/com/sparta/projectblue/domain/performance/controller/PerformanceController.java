package com.sparta.projectblue.domain.performance.controller;

import com.sparta.projectblue.config.ApiResponse;
import com.sparta.projectblue.domain.performance.service.PerformanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/performances")
@RequiredArgsConstructor
@Tag(name = "Performance", description = "공연 API")
public class PerformanceController {

    private final PerformanceService performanceService;

    @GetMapping("/{id}/rounds")
    @Operation(summary = "공연 회차 조회", description = "공연에 해당하는 회차와 예매 가능 상태를 조회함")
    public ResponseEntity<ApiResponse<?>> getRounds(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(performanceService.getRounds(id)));
    }


}
