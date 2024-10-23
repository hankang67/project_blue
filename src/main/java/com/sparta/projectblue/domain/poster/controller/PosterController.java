package com.sparta.projectblue.domain.poster.controller;

import com.sparta.projectblue.config.ApiResponse;
import com.sparta.projectblue.domain.common.dto.AuthUser;
import com.sparta.projectblue.domain.poster.dto.PosterRequestDto;
import com.sparta.projectblue.domain.poster.dto.PosterUpdateRequestDto;
import com.sparta.projectblue.domain.poster.service.PosterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "포스터", description = "포스터 CRUD")
public class PosterController {

    private final PosterService posterService;

    @Operation(summary = "포스터 등록", description = "특정 공연에 포스터를 등록")
    @PostMapping("/admin/posters")
    public ResponseEntity<ApiResponse<String>> create(
            @AuthenticationPrincipal AuthUser authUser,
            @Valid @RequestBody PosterRequestDto requestDto) {
        return ResponseEntity.ok(ApiResponse.success(posterService.create(authUser, requestDto)));
    }

    @Operation(summary = "포스터 수정", description = "특정 공연의 포스터 이름과 url 수정")
    @PutMapping("/admin/posters/{posterId}")
    public ResponseEntity<ApiResponse<String>> update(
            @AuthenticationPrincipal AuthUser authUser,
            @PathVariable Long posterId,
            @Valid @RequestBody PosterUpdateRequestDto requestDto) {
        return ResponseEntity.ok(ApiResponse.success(posterService.update(authUser, posterId, requestDto)));
    }

    @Operation(summary = "포스터 삭제", description = "특정 공연의 포스터 삭제")
    @DeleteMapping("/admin/posters/{posterId}")
    public ResponseEntity<ApiResponse<String>> delete(
            @AuthenticationPrincipal AuthUser authUser,
            @PathVariable Long posterId) {
        return ResponseEntity.ok(ApiResponse.success(posterService.delete(authUser, posterId)));
    }
}
