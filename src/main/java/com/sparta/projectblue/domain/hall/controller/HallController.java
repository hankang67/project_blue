package com.sparta.projectblue.domain.hall.controller;

import com.sparta.projectblue.config.ApiResponse;
import com.sparta.projectblue.domain.hall.dto.HallResponseDto;
import com.sparta.projectblue.domain.hall.dto.HallsResponseDto;
import com.sparta.projectblue.domain.hall.service.HallService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/halls")
public class HallController {

    private final HallService hallService;

    @GetMapping
    @Operation(summary = "공연장 다건 조회")
    public ResponseEntity<ApiResponse<List<HallsResponseDto>>> getHalls() {

        List<HallsResponseDto> hallsResponseDto = hallService.getHalls();

        return ResponseEntity.ok(ApiResponse.success(hallsResponseDto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "공연장 단건 조회")
    public ResponseEntity<ApiResponse<HallResponseDto>> getHall(@PathVariable("id") Long id) {

        HallResponseDto hallResponseDto = hallService.getHall(id);

        return ResponseEntity.ok(ApiResponse.success(hallResponseDto));
    }
}





