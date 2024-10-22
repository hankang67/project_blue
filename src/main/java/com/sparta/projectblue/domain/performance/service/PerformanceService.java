package com.sparta.projectblue.domain.performance.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.projectblue.domain.hall.entity.QHall;
import com.sparta.projectblue.domain.performance.dto.PerformanceDetailDto;
import com.sparta.projectblue.domain.performance.entity.QPerformance;
import com.sparta.projectblue.domain.performance.repository.PerformanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PerformanceService {

    private final PerformanceRepository performanceRepository;
    private final JPAQueryFactory queryFactory;

    //공연 상세 정보 조회
    public PerformanceDetailDto getPerformanceById(Long id){
        return performanceRepository.findPerformanceDetailById(id, queryFactory);
    }
}
