package com.sparta.projectblue.domain.performance.controller;

import com.sparta.projectblue.config.ApiResponse;
import com.sparta.projectblue.domain.common.dto.AuthUser;
import com.sparta.projectblue.domain.performance.dto.PerformanceRequestDto;
import com.sparta.projectblue.domain.performance.dto.PerformanceUpdateRequestDto;
import com.sparta.projectblue.domain.performance.service.PerformanceAdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/performances")
@Tag(name = "PerformanceAdmin", description = "관리자 전용 공연 API")
public class PerformanceAdminController {

    private final PerformanceAdminService performanceAdminService;

    @PostMapping
    @Operation(summary = "공연 등록")
    public ResponseEntity<ApiResponse<String>> create(
            @AuthenticationPrincipal AuthUser authUser,
            @RequestPart("data")  PerformanceRequestDto requestDto,
            @RequestPart("file") MultipartFile posterFile) {
        return ResponseEntity.ok(ApiResponse.success(performanceAdminService.create(authUser, requestDto, posterFile)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "공연 수정")
    public ResponseEntity<ApiResponse<String>> update(
            @AuthenticationPrincipal AuthUser authUser,
            @PathVariable Long id,
            @Valid @RequestBody PerformanceUpdateRequestDto requestDto) {
        return ResponseEntity.ok(ApiResponse.success(performanceAdminService.update(authUser, id, requestDto)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "공연 삭제")
    public ResponseEntity<ApiResponse<String>> delete(
            @AuthenticationPrincipal AuthUser authUser,
            @PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(performanceAdminService.delete(authUser, id)));
    }


}
