package com.sparta.projectblue.domain.performer.controller;

import com.sparta.projectblue.config.ApiResponse;
import com.sparta.projectblue.domain.common.dto.AuthUser;
import com.sparta.projectblue.domain.performer.dto.PerformerDetailDto;
import com.sparta.projectblue.domain.performer.service.PerformerAdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
            @Valid @RequestBody PerformerDetailDto requestDto) {
        PerformerDetailDto.Response response = performerAdminService.createPerformer(authUser, requestDto);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @PutMapping("/{id}")
    @Operation(summary = "배우 정보 수정", description = "기존 배우 정보를 수정합니다.")
    public ResponseEntity<ApiResponse<?>> updatePerformer(
            @AuthenticationPrincipal AuthUser authUser,
            @PathVariable Long id,
            @Valid @RequestBody PerformerDetailDto requestDto) {
        PerformerDetailDto.Response response = performerAdminService.updatePerformer(authUser, id, requestDto);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "배우 삭제", description = "기존 배우 정보를 삭제합니다.")
    public ResponseEntity<ApiResponse<?>> deletePerformer(
            @AuthenticationPrincipal AuthUser authUser,
            @PathVariable Long id) {
        performerAdminService.deletePerformer(authUser, id);
        return ResponseEntity.ok(ApiResponse.successWithNoContent());
    }


}
