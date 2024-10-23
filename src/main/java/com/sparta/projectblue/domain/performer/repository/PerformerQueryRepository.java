package com.sparta.projectblue.domain.performer.repository;

import com.sparta.projectblue.domain.performer.dto.PerformerDetailDto;

import java.util.List;

public interface PerformerQueryRepository {
    List<PerformerDetailDto> findPerformersByPerformanceId(Long performanceId);
}
