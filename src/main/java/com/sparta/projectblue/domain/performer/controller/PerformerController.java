package com.sparta.projectblue.domain.performer.controller;

import com.sparta.projectblue.config.ApiResponse;
import com.sparta.projectblue.domain.performer.dto.PerformerDetailDto;
import com.sparta.projectblue.domain.performer.service.PerformerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/performers")
@RequiredArgsConstructor
@Tag(name = "Performer", description = "출연자 관리 API")
public class PerformerController {

    private final PerformerService performerService;

    @PostMapping
    @Operation(summary = "출연자 등록", description = "새로운 출연자를 등록합니다.")
    public ResponseEntity<ApiResponse<PerformerDetailDto.Response>> createPerformer(
            @Valid @RequestBody PerformerDetailDto requestDto) {
        PerformerDetailDto.Response response = performerService.createPerformer(requestDto);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @GetMapping("/{id}")
    @Operation(summary = "출연자 조회", description = "출연자 정보를 조회합니다.")
    public ResponseEntity<ApiResponse<PerformerDetailDto.Response>> getPerformer(@PathVariable Long id) {
        PerformerDetailDto.Response response = performerService.getPerformer(id);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

}
