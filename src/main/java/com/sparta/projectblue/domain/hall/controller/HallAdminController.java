package com.sparta.projectblue.domain.hall.controller;

import com.sparta.projectblue.config.ApiResponse;
import com.sparta.projectblue.domain.common.dto.AuthUser;
import com.sparta.projectblue.domain.hall.dto.HallRequestDto;
import com.sparta.projectblue.domain.hall.service.HallAdminService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/halls")
@RequiredArgsConstructor
public class HallAdminController {

    private final HallAdminService hallAdminService;

    @PostMapping
    @Operation(summary = "공연장 등록")
    public ResponseEntity<ApiResponse<?>> create(
            @AuthenticationPrincipal AuthUser authUser,
            @Valid @RequestBody HallRequestDto request) {

        return ResponseEntity.ok(ApiResponse.success(hallAdminService.create(authUser, request)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "공연장 수정")
    public ResponseEntity<ApiResponse<?>> update(
            @AuthenticationPrincipal AuthUser authUser,
            @PathVariable("id") Long id,
            @Valid @RequestBody HallRequestDto request) {

        return ResponseEntity.ok(ApiResponse.success(hallAdminService.update(authUser, id, request)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "공연장 삭제")
    public ResponseEntity<ApiResponse<?>> delete(
            @AuthenticationPrincipal AuthUser authUser,
            @PathVariable("id") Long id) {

        hallAdminService.delete(authUser, id);

        return ResponseEntity.ok(ApiResponse.successWithNoContent());
    }
}
