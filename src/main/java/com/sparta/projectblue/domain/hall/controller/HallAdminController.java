package com.sparta.projectblue.domain.hall.controller;

import com.sparta.projectblue.config.ApiResponse;
import com.sparta.projectblue.domain.common.dto.AuthUser;
import com.sparta.projectblue.domain.hall.dto.HallRequestDto;
import com.sparta.projectblue.domain.hall.service.HallService;
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

    private final HallService hallService;

    @PostMapping
    @Operation(summary = "공연장 등록")
    public ResponseEntity<ApiResponse<?>> create(
            @AuthenticationPrincipal AuthUser authUser,
            @Valid @RequestBody HallRequestDto hallRequestDto) {
        return ResponseEntity.ok(ApiResponse.success(hallService.create(authUser, hallRequestDto)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "공연장 수정")
    public ResponseEntity<ApiResponse<?>> update(
            @AuthenticationPrincipal AuthUser authUser,
            @PathVariable Long id,
            @Valid @RequestBody HallRequestDto hallRequestDto) {
        return ResponseEntity.ok(ApiResponse.success(hallService.update(authUser, hallRequestDto)));
    }


    @GetMapping("/{id}")
    @Operation(summary = "공연장 삭제")
    public ResponseEntity<ApiResponse<?>> delete(
            @AuthenticationPrincipal AuthUser authUser,
            @PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.successWithNoContent());
    }

}
