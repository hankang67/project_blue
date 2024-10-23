package com.sparta.projectblue.domain.performance.repository;

import com.sparta.projectblue.domain.performance.dto.PerformanceResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface PerformanceRepositoryCustom {
    Page<PerformanceResponseDto> findByCondition(Pageable pageable, String performanceNm, LocalDateTime performanceDay, String performer);
}
