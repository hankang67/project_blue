package com.sparta.projectblue.domain.performer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.projectblue.config.ApiResponse;
import com.sparta.projectblue.domain.performer.service.PerformerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/performers")
@RequiredArgsConstructor
@Tag(name = "Performer", description = "배우 관리 API")
public class PerformerController {

    private final PerformerService performerService;

    @GetMapping("/{id}")
    @Operation(summary = "배우 단건 조회", description = "배우 정보를 조회합니다.")
    public ResponseEntity<ApiResponse<?>> getPerformer(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(performerService.getPerformer(id)));
    }

    @GetMapping
    @Operation(summary = "배우 다건 조회", description = "배우를 전체 조회합니다.")
    public ResponseEntity<ApiResponse<?>> getPerformers() {
        return ResponseEntity.ok(ApiResponse.success(performerService.getPerformers()));
    }
}
