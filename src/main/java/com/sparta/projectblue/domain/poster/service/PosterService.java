package com.sparta.projectblue.domain.poster.service;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.sparta.projectblue.domain.common.dto.AuthUser;
import com.sparta.projectblue.domain.performance.service.PerformanceAdminService;
import com.sparta.projectblue.domain.poster.dto.UpdatePosterResponseDto;
import com.sparta.projectblue.domain.poster.entity.Poster;
import com.sparta.projectblue.domain.poster.repository.PosterRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class PosterService {

    private final PosterRepository posterRepository;

    private final PerformanceAdminService performanceAdminService;

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Transactional
    public UpdatePosterResponseDto update(AuthUser authUser, Long posterId, MultipartFile file) {

        performanceAdminService.hasRole(authUser);

        Poster poster = getPoster(posterId);

        // aws 에 등록되어 있는 이미지를 삭제
        log.info("Deleting poster file: " + poster.getName() + " from bucket: " + bucket);
        amazonS3.deleteObject(new DeleteObjectRequest(bucket, poster.getName()));
        log.info("Poster file deleted successfully from S3.");

        // 새로운 이미지 s3에 등록
        String posterName =
                "images/" + performanceAdminService.createFileName(file.getOriginalFilename());
        String posterUrl;

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        try (InputStream inputStream = file.getInputStream()) {
            log.info("Uploading file: " + posterName + " to bucket: " + bucket);
            amazonS3.putObject(new PutObjectRequest(bucket, posterName, inputStream, metadata));
            log.info("File uploaded successfully.");

            posterUrl = amazonS3.getUrl(bucket, posterName).toString();

        } catch (IOException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "새 이미지 업로드에 실패했습니다.");
        }

        poster.update(posterName, posterUrl, poster.getFileSize());

        return new UpdatePosterResponseDto(poster);
    }

    private Poster getPoster(Long posterId) {

        return posterRepository
                .findById(posterId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 포스터입니다."));
    }
}
