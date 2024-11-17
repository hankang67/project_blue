package com.sparta.projectblue.domain.reservation.controller;

import com.sparta.projectblue.config.ApiResponse;
import com.sparta.projectblue.domain.common.dto.AuthUser;
import com.sparta.projectblue.domain.reservation.dto.*;
import com.sparta.projectblue.domain.reservation.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
@Tag(name = "Reservation", description = "공연 예매 관련 API")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    @Operation(summary = "공연 예매")
    public ResponseEntity<ApiResponse<CreateReservationResponseDto>> create(
            @AuthenticationPrincipal AuthUser authUser,
            @Valid @RequestBody CreateReservationRequestDto request)
            throws InterruptedException {

        return ResponseEntity.ok(
                ApiResponse.success(reservationService.create(authUser.getId(), request)));
    }

    @DeleteMapping
    @Operation(summary = "예매 취소", description = "예매 취소 api, 비밀번호 입력 필수")
    public ResponseEntity<ApiResponse<Void>> delete(
            @AuthenticationPrincipal AuthUser authUser,
            @Valid @RequestBody DeleteReservationRequestDto request)
            throws Exception {

        reservationService.delete(authUser.getId(), request);

        return ResponseEntity.ok(ApiResponse.successWithNoContent());
    }

    @GetMapping
    @Operation(summary = "예매 다건 조회", description = "authUser가 예매한 내역 다건")
    public ResponseEntity<ApiResponse<List<GetReservationsResponseDto>>> getReservations(
            @AuthenticationPrincipal AuthUser authUser) {

        return ResponseEntity.ok(
                ApiResponse.success(reservationService.getReservations(authUser.getId())));
    }

    @GetMapping("/{id}")
    @Operation(summary = "예매 단건 조회", description = "authUser가 예매한 내역 단건")
    public ResponseEntity<ApiResponse<GetReservationResponseDto>> getReservation(
            @AuthenticationPrincipal AuthUser authUser, @PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.success(reservationService.getReservation(authUser, id)));
    }
}
