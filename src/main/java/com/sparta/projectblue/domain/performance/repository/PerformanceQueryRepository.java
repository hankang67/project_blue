package com.sparta.projectblue.domain.performance.repository;

import com.sparta.projectblue.domain.performance.dto.GetPerformancePerformersResponseDto;
import com.sparta.projectblue.domain.performance.dto.GetPerformancesResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface PerformanceQueryRepository {
    Page<GetPerformancesResponseDto> findByCondition(Pageable pageable, String performanceNm, LocalDateTime performanceDay, String performer);

    List<GetPerformancePerformersResponseDto.PerformerInfo> findPerformersByPerformanceId(Long performanceId);
}
