package com.sparta.projectblue.domain.performance.controller;

import com.sparta.projectblue.domain.performance.dto.PerformanceDetailDto;
import com.sparta.projectblue.domain.performance.service.PerformanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/performances")
@RequiredArgsConstructor
public class PerformanceController {

    private final PerformanceService performanceService;

    @GetMapping("/{id}")
    public ResponseEntity<PerformanceDetailDto> getPerformanceById(
            @PathVariable Long id) {
        PerformanceDetailDto dto = performanceService.getPerformanceById(id);
        return ResponseEntity.ok(dto);
    }

}
