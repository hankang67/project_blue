package com.sparta.projectblue.domain.performance.controller;

import com.sparta.projectblue.config.ApiResponse;
import com.sparta.projectblue.domain.common.dto.AuthUser;
import com.sparta.projectblue.domain.performance.dto.PerformanceRequestDto;
import com.sparta.projectblue.domain.performance.dto.PerformanceResponseDto;
import com.sparta.projectblue.domain.performance.service.PerformanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Performance API", description = "공연")
public class PerformanceController {

    private final PerformanceService performanceService;

    @PostMapping("/admin/performances")
    @Operation(summary = "공연 등록")
    public ResponseEntity<ApiResponse<String>> create(
            @AuthenticationPrincipal AuthUser authUser,
            @Valid @RequestBody PerformanceRequestDto requestDto) {
        return ResponseEntity.ok(ApiResponse.success(performanceService.create(authUser, requestDto)));
    }

    @DeleteMapping("/admin/performances/{performanceId}")
    @Operation(summary = "공연 삭제")
    public ResponseEntity<ApiResponse<String>> delete(
            @AuthenticationPrincipal AuthUser authUser,
            @PathVariable Long performanceId) {
        return ResponseEntity.ok(ApiResponse.success(performanceService.delete(authUser, performanceId)));
    }

    // 전체 조회
    @GetMapping("/performances")
    @Operation(summary = "전체 공연리스트 조회", description = "현재 진행중인 공연 리스트 전체 출력")
    public ResponseEntity<ApiResponse<Page<PerformanceResponseDto>>> getPerformances(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(ApiResponse.success(performanceService.getPerformances(page, size)));
    }

    // 필터링해서 검색
    @GetMapping("/performances/filters")
    @Operation(summary = "공연리스트 필터 조회", description = "현재 진행중인 공연 리스트 조건에 따라 출력")
    public ResponseEntity<ApiResponse<Page<PerformanceResponseDto>>> getFilterPerformances(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String performanceNm,
            @RequestParam(required = false) String userSelectDay,
            @RequestParam(required = false) String performer
    ) {
        return ResponseEntity.ok(ApiResponse.success(performanceService.getFilterPerformances(page, size, performanceNm, userSelectDay, performer)));
    }
}
