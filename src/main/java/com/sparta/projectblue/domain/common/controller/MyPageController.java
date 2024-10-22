package com.sparta.projectblue.domain.common.controller;

import com.sparta.projectblue.domain.user.dto.ReservationDetailDto;
import com.sparta.projectblue.domain.user.dto.ReservationDto;
import com.sparta.projectblue.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
@Tag(name = "Mypage", description = "마이페이지 API")
public class MyPageController {

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
        ReservationDetailDto.Response reservation = userService.getReservationDetail(userId,reservationId);
        return ResponseEntity.ok(reservation);
    }

}