package com.sparta.projectblue.domain.performance.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.sparta.projectblue.domain.common.dto.AuthUser;
import com.sparta.projectblue.domain.common.enums.UserRole;
import com.sparta.projectblue.domain.hall.repository.HallRepository;
import com.sparta.projectblue.domain.performance.dto.PerformanceRequestDto;
import com.sparta.projectblue.domain.performance.dto.PerformanceUpdateRequestDto;
import com.sparta.projectblue.domain.performance.entity.Performance;
import com.sparta.projectblue.domain.performance.repository.PerformanceRepository;
import com.sparta.projectblue.domain.performer.repository.PerformerRepository;
import com.sparta.projectblue.domain.performerPerformance.entity.PerformerPerformance;
import com.sparta.projectblue.domain.performerPerformance.repository.PerformerPerformanceRepository;
import com.sparta.projectblue.domain.poster.entity.Poster;
import com.sparta.projectblue.domain.poster.repository.PosterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class PerformanceAdminService {

    private final PerformanceRepository performanceRepository;

    private final HallRepository hallRepository;
    private final PerformerRepository performerRepository;
    private final PerformerPerformanceRepository performerPerformanceRepository;
    private final PosterRepository posterRepository;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;


    @Transactional
    public String create(AuthUser authUser, PerformanceRequestDto requestDto, MultipartFile posterFile) {
        hasRole(authUser);

        if (!hallRepository.existsById(requestDto.getHallId())) {
            throw new IllegalArgumentException("존재하지 않는 공연장입니다.");
        }

        LocalDateTime startDate = LocalDate.parse(requestDto.getStartDate()).atStartOfDay();
        LocalDateTime endDate = LocalDate.parse(requestDto.getEndDate()).atTime(LocalTime.MAX);

        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("종료일이 시작일보다 빠를 수 없습니다.");
        }

        Performance performance = new Performance(
                requestDto.getHallId(),
                requestDto.getTitle(),
                startDate,
                endDate,
                requestDto.getPrice(),
                requestDto.getCategory(),
                requestDto.getDescription(),
                requestDto.getDuration());

        // 공연 등록
        Performance savedPerformance = performanceRepository.save(performance);

        // 출연자 등록
        for (Long performerId : requestDto.getPerformers()) {
            if (!performerRepository.existsById(performerId)) {
                throw new IllegalArgumentException("존재하지 않는 출연자입니다.");
            }

            PerformerPerformance per = new PerformerPerformance(performerId, savedPerformance.getId());
            performerPerformanceRepository.save(per);
        }

        // aws 추가
        String posterName = "images/" + createFileName(posterFile.getOriginalFilename());

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(posterFile.getSize());
        objectMetadata.setContentType(posterFile.getContentType());

        String posterUrl;

        try (InputStream inputStream = posterFile.getInputStream()) {
            log.info("Uploading file: " + posterName + " to bucket: " + bucket);
            amazonS3.putObject(new PutObjectRequest(bucket, posterName, inputStream, objectMetadata));
            log.info("File uploaded successfully.");

            posterUrl = amazonS3.getUrl(bucket, posterName).toString();

        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드에 실패했습니다.");
        }


        // 포스터 등록
        Poster poster = new Poster(savedPerformance.getId(), posterName, posterUrl, posterFile.getSize());
        posterRepository.save(poster);


        return "공연 ID : " + savedPerformance.getId() + ", 공연 이름 : " + savedPerformance.getTitle();
    }

    @Transactional
    public String update(AuthUser authUser, Long performanceId, PerformanceUpdateRequestDto requestDto) {
        hasRole(authUser);

        Performance performance = performanceRepository.findById(performanceId).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 공연입니다."));

        // 공연 정보 수정
        performance.update(requestDto);

        // 출연자 리스트 전체 삭제 후 다시 추가
        deletePerformerPerformance(performanceId);

        for (Long performerId : requestDto.getPerformers()) {
            if (!performerRepository.existsById(performerId)) {
                throw new IllegalArgumentException("존재하지 않는 출연자입니다.");
            }

            PerformerPerformance per = new PerformerPerformance(performerId, performanceId);
            performerPerformanceRepository.save(per);
        }

        return "공연 ID : " + performance.getId() + ", 공연 이름 : " + performance.getTitle() + " 수정 완료.";
    }

    @Transactional
    public String delete(AuthUser authUser, Long performanceId) {
        hasRole(authUser);

        List<Performance> performances = performanceRepository.findAllById(performanceId);

        System.out.println("List size : " + performances.size());

        if (performances.isEmpty()) {
            throw new IllegalArgumentException("해당 공연이 존재하지 않습니다.");
        }

        performanceRepository.deleteAll(performances);

        // 공연, 출연자 테이블 연관데이터 삭제
        deletePerformerPerformance(performanceId);

        // 포스터 테이블 삭제
        Poster poster = posterRepository.findByPerformanceId(performanceId).orElseThrow(() ->
                new IllegalArgumentException("공연에 대한 포스터가 없습니다."));

        log.info("Deleting poster file: " + poster.getName() + " from bucket: " + bucket);
        amazonS3.deleteObject(new DeleteObjectRequest(bucket, poster.getName()));
        log.info("Poster file deleted successfully from S3.");

        posterRepository.delete(poster);

        return "공연이 삭제되었습니다.";
    }

    public void hasRole(AuthUser authUser) {
        if (!authUser.hasRole(UserRole.ROLE_ADMIN)) {
            throw new IllegalArgumentException("관리자만 접근할 수 있습니다.");
        }
    }

    private void deletePerformerPerformance(Long performanceId) {
        List<PerformerPerformance> performerPerformances = performerPerformanceRepository.findAllByPerformanceId(performanceId);

        if (performerPerformances.isEmpty()) {
            throw new IllegalArgumentException("출연자가 존재하지 않습니다.");
        }

        performerPerformanceRepository.deleteAll(performerPerformances);
    }



    public String createFileName(String fileName) {
        // aws 업로드를 위한 unique name 생성
        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

    private String getFileExtension(String fileName) {
        try {
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 형식의 파일(" + fileName + ") 입니다.");
        }
    }



}
