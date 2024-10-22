package com.sparta.projectblue.domain.performance.controller;

import com.sparta.projectblue.domain.performance.dto.PerformanceDetailDto;
import com.sparta.projectblue.domain.performance.service.PerformanceService;
import com.sparta.projectblue.domain.performer.dto.PerformerDetailDto;
import com.sparta.projectblue.domain.performer.service.PerformerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/performances")
@RequiredArgsConstructor
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

}
