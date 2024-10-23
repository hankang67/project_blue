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
@Tag(name = "Performer", description = "배우 관리 API")
public class PerformerController {

    private final PerformerService performerService;

    @PostMapping
    @Operation(summary = "배우 등록", description = "새로운 배우를 등록합니다.")
    public ResponseEntity<ApiResponse<PerformerDetailDto.Response>> createPerformer(
            @Valid @RequestBody PerformerDetailDto requestDto) {
        PerformerDetailDto.Response response = performerService.createPerformer(requestDto);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @GetMapping("/{id}")
    @Operation(summary = "배우 조회", description = "배우 정보를 조회합니다.")
    public ResponseEntity<ApiResponse<PerformerDetailDto.Response>> getPerformer(@PathVariable Long id) {
        PerformerDetailDto.Response response = performerService.getPerformer(id);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @PutMapping("/{id}")
    @Operation(summary = "배우 정보 수정", description = "기존 배우 정보를 수정합니다.")
    public ResponseEntity<ApiResponse<PerformerDetailDto.Response>> updatePerformer(
            @PathVariable Long id,
            @Valid @RequestBody PerformerDetailDto requestDto) {
        PerformerDetailDto.Response response = performerService.updatePerformer(id, requestDto);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "배우 삭제", description = "기존 배우 정보를 삭제합니다.")
    public ResponseEntity<ApiResponse<String>> deletePerformer(@PathVariable Long id) {
        performerService.deletePerformer(id);
        return ResponseEntity.ok(ApiResponse.success("출연자가 성공적으로 삭제되었습니다."));
    }



}
