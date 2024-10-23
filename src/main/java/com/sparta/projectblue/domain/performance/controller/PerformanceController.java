package com.sparta.projectblue.domain.performance.controller;

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
@RequestMapping("/performances")
@RequiredArgsConstructor
@Tag(name = "Performance", description = "공연 API")
public class PerformanceController {

    private final PerformanceService performanceService;

    private final PerformerService performerService;

    @GetMapping("/{id}")
    public ResponseEntity<PerformanceDetailDto> getPerformanceById(
            @PathVariable Long id) {
        PerformanceDetailDto dto = performanceService.getPerformanceById(id);
        return ResponseEntity.ok(dto);
    }

    // 공연에 대한 출연자 목록 조회
    @GetMapping("/{id}/performers")
    public ResponseEntity<List<PerformerDetailDto>> getPerformersByPerformanceId(
            @PathVariable Long id) {
        List<PerformerDetailDto> performers = performerService.getPerformersByPerformanceId(id);
        return ResponseEntity.ok(performers);
    }


    @GetMapping("/{id}/rounds")
    @Operation(summary = "공연 회차 조회", description = "공연에 해당하는 회차와 예매 가능 상태를 조회함")
    public ResponseEntity<ApiResponse<?>> getRounds(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(performanceService.getRounds(id)));
    }

}
