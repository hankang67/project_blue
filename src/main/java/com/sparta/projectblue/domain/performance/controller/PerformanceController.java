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

import com.sparta.projectblue.domain.performance.dto.PerformanceDetailDto;
import com.sparta.projectblue.domain.performance.service.PerformanceService;
import com.sparta.projectblue.domain.performer.dto.PerformerDetailDto;
import com.sparta.projectblue.domain.performer.service.PerformerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
@RequiredArgsConstructor
@Tag(name = "Performance", description = "공연 API")
public class PerformanceController {

    private final PerformanceService performanceService;
    private final PerformerService performerService;


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
    @GetMapping("/performances/filter")
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

    @GetMapping("/performances/{id}")
    public ResponseEntity<PerformanceDetailDto> getPerformanceById(
            @PathVariable Long id) {
        PerformanceDetailDto dto = performanceService.getPerformanceById(id);
        return ResponseEntity.ok(dto);
    }

    // 공연에 대한 출연자 목록 조회
    @GetMapping("/performances/{id}/performers")
    public ResponseEntity<List<PerformerDetailDto>> getPerformersByPerformanceId(
            @PathVariable Long id) {
        List<PerformerDetailDto> performers = performerService.getPerformersByPerformanceId(id);
        return ResponseEntity.ok(performers);
    }


    @GetMapping("/performances/{id}/rounds")
    @Operation(summary = "공연 회차 조회", description = "공연에 해당하는 회차와 예매 가능 상태를 조회함")
    public ResponseEntity<ApiResponse<?>> getRounds(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(performanceService.getRounds(id)));
    }
}
