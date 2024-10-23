package com.sparta.projectblue.domain.user.controller;

import com.sparta.projectblue.config.ApiResponse;
import com.sparta.projectblue.domain.common.dto.AuthUser;
import com.sparta.projectblue.domain.user.dto.DeleteUserDto;
import com.sparta.projectblue.domain.user.dto.ReservationDetailDto;
import com.sparta.projectblue.domain.user.dto.ReservationDto;
import com.sparta.projectblue.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    // 예매 전체 조회
    @GetMapping("/{userId}/reservations")
    public ResponseEntity<List<ReservationDto.Response>> getReservations(@PathVariable Long userId) {
        List<ReservationDto.Response> reservations = userService.getReservations(userId);
        return ResponseEntity.ok(reservations);
    }

    // 예매 상세 조회
    @GetMapping("/{userId}/reservations/{reservationId}")
    public ResponseEntity<ReservationDetailDto.Response> getReservationDetail(@PathVariable Long userId, @PathVariable Long reservationId) {
        ReservationDetailDto.Response reservation = userService.getReservationDetail(userId, reservationId);
        return ResponseEntity.ok(reservation);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<?>> deleteUser(
            @AuthenticationPrincipal AuthUser authUser,
            @Valid @RequestBody DeleteUserDto.Request deleteUserRequest) {

        userService.deleteUser(authUser, deleteUserRequest);

        return ResponseEntity.ok(ApiResponse.successWithNoContent());
    }
}
