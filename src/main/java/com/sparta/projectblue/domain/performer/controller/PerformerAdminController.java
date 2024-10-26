package com.sparta.projectblue.domain.performer.controller;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.sparta.projectblue.config.ApiResponse;
import com.sparta.projectblue.domain.common.dto.AuthUser;
import com.sparta.projectblue.domain.performer.dto.CreatePerformerRequestDto;
import com.sparta.projectblue.domain.performer.dto.UpdatePerformerRequestDto;
import com.sparta.projectblue.domain.performer.service.PerformerAdminService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/performers")
@RequiredArgsConstructor
@Tag(name = "PerformerAdmin", description = "관리자 전용 배우 API")
public class PerformerAdminController {

    private final PerformerAdminService performerAdminService;

    @PostMapping
    @Operation(summary = "배우 등록", description = "새로운 배우를 등록합니다.")
    public ResponseEntity<ApiResponse<?>> createPerformer(
            @AuthenticationPrincipal AuthUser authUser,
            @Valid @RequestBody CreatePerformerRequestDto request) {
        return ResponseEntity.ok(
                ApiResponse.success(performerAdminService.createPerformer(authUser, request)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "배우 정보 수정", description = "기존 배우 정보를 수정합니다.")
    public ResponseEntity<ApiResponse<?>> updatePerformer(
            @AuthenticationPrincipal AuthUser authUser,
            @PathVariable Long id,
            @Valid @RequestBody UpdatePerformerRequestDto request) {
        return ResponseEntity.ok(
                ApiResponse.success(performerAdminService.updatePerformer(authUser, id, request)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "배우 삭제", description = "기존 배우 정보를 삭제합니다.")
    public ResponseEntity<ApiResponse<?>> deletePerformer(
            @AuthenticationPrincipal AuthUser authUser, @PathVariable Long id) {
        performerAdminService.deletePerformer(authUser, id);
        return ResponseEntity.ok(ApiResponse.successWithNoContent());
    }
}
