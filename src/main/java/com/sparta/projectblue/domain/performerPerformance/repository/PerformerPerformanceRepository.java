package com.sparta.projectblue.domain.performerPerformance.repository;

import com.sparta.projectblue.domain.performerPerformance.entity.PerformerPerformance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PerformerPerformanceRepository extends JpaRepository<PerformerPerformance, Long> {
    List<PerformerPerformance> findAllByPerformanceId(Long performanceId);

    boolean existsByPerformanceIdAndPerformerId(Long performanceId, Long performerId);
    Optional<PerformerPerformance> findByPerformanceIdAndPerformerId(Long performanceId, Long performerId);
}
