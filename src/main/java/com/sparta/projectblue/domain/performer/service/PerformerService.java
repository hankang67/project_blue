package com.sparta.projectblue.domain.performer.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.projectblue.domain.performance.dto.PerformanceDetailDto;
import com.sparta.projectblue.domain.performer.controller.PerformerController;
import com.sparta.projectblue.domain.performer.dto.PerformerDetailDto;
import com.sparta.projectblue.domain.performer.repository.PerformerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PerformerService {

    private final PerformerRepository performerRepository;
    private final JPAQueryFactory queryFactory;

    // 공연 ID로 출연자 목록 조회
    public List<PerformerDetailDto> getPerformersByPerformanceId(Long performanceId) {
        return performerRepository.findPerformersByPerformanceId(performanceId, queryFactory);
    }
}
