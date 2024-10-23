package com.sparta.projectblue.domain.performerPerformance.repository;

import com.sparta.projectblue.domain.performance.entity.Performance;
import com.sparta.projectblue.domain.performer.entity.Performer;
import com.sparta.projectblue.domain.performerPerformance.entity.PerformerPerformance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PerformerPerformanceRepository extends JpaRepository<PerformerPerformance, Long> {
    List<PerformerPerformance> findAllByPerformanceId(Long performanceId);

    boolean existsByPerformanceIdAndPerformerId(Long performanceId, Long performerId);
}
