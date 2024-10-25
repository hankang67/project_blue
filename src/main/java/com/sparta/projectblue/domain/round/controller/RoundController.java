package com.sparta.projectblue.domain.round.controller;

import com.sparta.projectblue.config.ApiResponse;
import com.sparta.projectblue.domain.common.enums.PerformanceStatus;
import com.sparta.projectblue.domain.round.dto.CreateRoundsDto;
import com.sparta.projectblue.domain.round.service.RoundService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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
    public ResponseEntity<ApiResponse<?>> createRound(
            @Valid @RequestBody CreateRoundsDto.Request requestDto) {
        // 2024-10-25T16:00:00
        List<CreateRoundsDto.Response> responseDto = roundService.createRounds(requestDto.getPerformanceId(), requestDto);

        return ResponseEntity.ok(ApiResponse.success(responseDto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "회차 수정", description = "특정 회차의 정보를 수정합니다.")
    public ResponseEntity<ApiResponse<?>> updateRound(
            @PathVariable Long id,
            @RequestParam LocalDateTime date,
            @RequestParam PerformanceStatus status) {

        CreateRoundsDto.UpdateRequest updateRequest = new CreateRoundsDto.UpdateRequest(date, status);
        CreateRoundsDto.Response responseDto = roundService.updateRound(id, updateRequest);

        return ResponseEntity.ok(ApiResponse.success(responseDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "회차 삭제", description = "특정 회차를 삭제합니다.")
    public ResponseEntity<ApiResponse<?>> deleteRound(@PathVariable Long id) {
        roundService.deleteRound(id);
        return ResponseEntity.ok(ApiResponse.successWithNoContent());
    }

}
