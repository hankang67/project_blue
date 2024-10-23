package com.sparta.projectblue.domain.performance.service;

import com.sparta.projectblue.domain.common.dto.AuthUser;
import com.sparta.projectblue.domain.common.enums.UserRole;
import com.sparta.projectblue.domain.hall.repository.HallRepository;
import com.sparta.projectblue.domain.performance.dto.PerformanceRequestDto;
import com.sparta.projectblue.domain.performance.entity.Performance;
import com.sparta.projectblue.domain.performance.repository.PerformanceRepository;
import com.sparta.projectblue.domain.performer.repository.PerformerRepository;
import com.sparta.projectblue.domain.performerperformance.entity.PerformerPerformance;
import com.sparta.projectblue.domain.performerperformance.repository.PerformerPerformanceRepository;
import com.sparta.projectblue.domain.poster.entity.Poster;
import com.sparta.projectblue.domain.poster.repository.PosterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PerformanceAdminService {

    private final PerformanceRepository performanceRepository;

    private final HallRepository hallRepository;
    private final PerformerRepository performerRepository;
    private final PerformerPerformanceRepository performerPerformanceRepository;
    private final PosterRepository posterRepository;

    @Transactional
    public String create(AuthUser authUser, PerformanceRequestDto requestDto) {
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

        // 포스터 등록
        Poster poster = new Poster(savedPerformance.getId(), requestDto.getPosterName(), requestDto.getPosterUrl());
        posterRepository.save(poster);

        return savedPerformance.getTitle();
    }

    // 공연 삭제
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
        List<PerformerPerformance> performerPerformances = performerPerformanceRepository.findAllByPerformanceId(performanceId);

        if (performerPerformances.isEmpty()) {
            throw new IllegalArgumentException("출연자가 존재하지 않습니다.");
        }

        performerPerformanceRepository.deleteAll(performerPerformances);

        // 포스터 테이블 삭제
        Poster poster = posterRepository.findByPerformanceId(performanceId).orElseThrow(() ->
                new IllegalArgumentException("공연에 대한 포스터가 없습니다."));

        posterRepository.delete(poster);

        return "공연이 삭제되었습니다.";
    }

    // 권한 확인
    public void hasRole(AuthUser authUser) {
        if (!authUser.hasRole(UserRole.ROLE_ADMIN)) {
            throw new IllegalArgumentException("관리자만 접근할 수 있습니다.");
        }
    }
}
