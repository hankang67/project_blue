package com.sparta.projectblue.domain.poster.controller;

import com.sparta.projectblue.config.ApiResponse;
import com.sparta.projectblue.domain.common.dto.AuthUser;
import com.sparta.projectblue.domain.poster.dto.PosterUpdateRequestDto;
import com.sparta.projectblue.domain.poster.service.AwsS3Service;
import com.sparta.projectblue.domain.poster.service.PosterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Poster", description = "포스터 CRUD API")
public class PosterController {

    private final AwsS3Service awsS3Service;

    private final PosterService posterService;

    // aws s3에 포스터 업로드
    @Operation(summary = "Amazon S3에 포스터 업로드", description = "특정 공연의 포스터 이름, url 등록")
    @PostMapping("/admin/posters")
    public ApiResponse<List<String>> uploadFile(@Parameter(description = "파일들(여러 파일 업로드 가능)", required = true) @RequestPart List<MultipartFile> multipartFile) {
        return ApiResponse.success(awsS3Service.uploadFile(multipartFile));
    }

    // aws s3에 포스터 업로드 삭제
    @Operation(summary = "Amazon S3에 포스터 업로드한 것 삭제", description = "특정 공연의 포스터 이름, url 등록한 것 삭제")
    @DeleteMapping("/admin/posters")
    public ApiResponse<Void> deleteFile(@Parameter(description = "Amazon S3에 업로드 된 파일을 삭제") @RequestParam String fileName){
        awsS3Service.deleteFile(fileName);
        return ApiResponse.success(null);
    }

    @Operation(summary = "포스터 수정", description = "특정 공연의 포스터 이름과 url 수정")
    @PutMapping("/admin/posters/{id}")
    public ResponseEntity<ApiResponse<String>> update(
            @AuthenticationPrincipal AuthUser authUser,
            @PathVariable Long id,
            @Valid @RequestBody PosterUpdateRequestDto requestDto) {
        return ResponseEntity.ok(ApiResponse.success(posterService.update(authUser, id, requestDto)));
    }
}
