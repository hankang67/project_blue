package com.sparta.projectblue.domain.performance.controller;


import com.sparta.projectblue.config.ApiResponse;
import com.sparta.projectblue.domain.performance.dto.PerformanceDetailDto;
import com.sparta.projectblue.domain.performance.dto.PerformanceResponseDto;
import com.sparta.projectblue.domain.performance.service.PerformanceService;
import com.sparta.projectblue.domain.performer.dto.PerformerDetailDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/performances")
@Tag(name = "Performance", description = "공연 API")
public class PerformanceController {

    private final PerformanceService performanceService;

    // 전체 조회
    @GetMapping
    @Operation(summary = "전체 공연리스트 조회", description = "현재 진행중인 공연 리스트 전체 출력")
    public ResponseEntity<ApiResponse<Page<PerformanceResponseDto>>> getPerformances(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(ApiResponse.success(performanceService.getPerformances(page, size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerformanceDetailDto> getPerformance(
            @PathVariable Long id) {
        PerformanceDetailDto dto = performanceService.getPerformance(id);
        return ResponseEntity.ok(dto);
    }

    // 공연에 대한 출연자 목록 조회
    @GetMapping("/{id}/performers")
    public ResponseEntity<List<PerformerDetailDto>> getPerformers(
            @PathVariable Long id) {
        List<PerformerDetailDto> performers = performanceService.getPerformers(id);
        return ResponseEntity.ok(performers);
    }

    @GetMapping("/{id}/rounds")
    @Operation(summary = "공연 회차 조회", description = "공연에 해당하는 전체 회차와 예매 가능 상태를 조회함")
    public ResponseEntity<ApiResponse<?>> getRounds(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(performanceService.getRounds(id)));
    }
}
