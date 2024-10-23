package com.sparta.projectblue.domain.user.controller;

import com.sparta.projectblue.config.ApiResponse;
import com.sparta.projectblue.domain.common.dto.AuthUser;
import com.sparta.projectblue.domain.user.dto.DeleteUserDto;
import com.sparta.projectblue.domain.user.dto.ReservationDetailDto;
import com.sparta.projectblue.domain.user.dto.ReservationDto;
import com.sparta.projectblue.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Tag(name = "User")
public class UserController {

    private final UserService userService;

    @DeleteMapping
    @Operation(summary = "탈퇴")
    public ResponseEntity<ApiResponse<?>> deleteUser(
            @AuthenticationPrincipal AuthUser authUser,
            @Valid @RequestBody DeleteUserDto.Request deleteUserRequest) {

        userService.deleteUser(authUser, deleteUserRequest);

        return ResponseEntity.ok(ApiResponse.successWithNoContent());
    }
}
