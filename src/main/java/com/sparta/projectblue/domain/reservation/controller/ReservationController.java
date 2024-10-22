package com.sparta.projectblue.domain.reservation.controller;

import com.sparta.projectblue.config.ApiResponse;
import com.sparta.projectblue.domain.common.dto.AuthUser;
import com.sparta.projectblue.domain.reservation.dto.CreateReservationDto;
import com.sparta.projectblue.domain.reservation.dto.DeleteReservationDto;
import com.sparta.projectblue.domain.reservation.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
@Tag(name = "Reservation", description = "공연 예매 API")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    @Operation(summary = "예매", description = "공연 예매에 사용하는 API")
    public ResponseEntity<ApiResponse<?>> create(
            @AuthenticationPrincipal AuthUser authUser,
            @Valid @RequestBody CreateReservationDto.Request request) {

        return ResponseEntity.ok(ApiResponse.success(reservationService.create(authUser.getId(), request)));
    }

    @DeleteMapping
    @Operation(summary = "예매취소", description = "예매취소 api")
    public ResponseEntity<ApiResponse<?>> delete(
            @AuthenticationPrincipal AuthUser authUser,
            @Valid @RequestBody DeleteReservationDto.Request request) {

        reservationService.delete(authUser.getId(), request);

        return ResponseEntity.ok(ApiResponse.successWithNoContent());
    }
}
