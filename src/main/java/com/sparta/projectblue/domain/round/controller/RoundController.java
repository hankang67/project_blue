package com.sparta.projectblue.domain.round.controller;

import java.time.LocalDateTime;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sparta.projectblue.config.ApiResponse;
import com.sparta.projectblue.domain.common.enums.PerformanceStatus;
import com.sparta.projectblue.domain.round.dto.CreateRoundRequestDto;
import com.sparta.projectblue.domain.round.dto.UpdateRoundRequestDto;
import com.sparta.projectblue.domain.round.service.RoundService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rounds")
@RequiredArgsConstructor
@Tag(name = "Round", description = "회차 관련 API")
public class RoundController {

    private final RoundService roundService;

    @GetMapping("/{id}")
    @Operation(summary = "예매가능 좌석 조회", description = "입력 회차의 예매 가능 좌석을 모두 출력")
    public ResponseEntity<ApiResponse<?>> getAvailableSeats(@PathVariable Long id) {

        return ResponseEntity.ok(ApiResponse.success(roundService.getAvailableSeats(id)));
    }

    @PostMapping
    @Operation(summary = "공연별 다건 회차 등록", description = "공연별 다건 회차를 등록합니다.")
    public ResponseEntity<ApiResponse<?>> create(
            @Valid @RequestBody CreateRoundRequestDto request) {

        return ResponseEntity.ok(
                ApiResponse.success(roundService.create(request.getPerformanceId(), request)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "회차 수정", description = "특정 회차의 정보를 수정합니다.")
    public ResponseEntity<ApiResponse<?>> update(
            @PathVariable Long id,
            @RequestParam LocalDateTime date,
            @RequestParam PerformanceStatus status) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        roundService.update(id, new UpdateRoundRequestDto(date, status))));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "회차 삭제", description = "특정 회차를 삭제합니다.")
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable Long id) {

        roundService.delete(id);

        return ResponseEntity.ok(ApiResponse.successWithNoContent());
    }
}
