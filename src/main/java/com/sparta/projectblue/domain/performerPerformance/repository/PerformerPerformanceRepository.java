package com.sparta.projectblue.domain.performerPerformance.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sparta.projectblue.domain.performerPerformance.entity.PerformerPerformance;

public interface PerformerPerformanceRepository extends JpaRepository<PerformerPerformance, Long> {

    List<PerformerPerformance> findAllByPerformanceId(Long performanceId);

    boolean existsByPerformanceIdAndPerformerId(Long performanceId, Long performerId);

    Optional<PerformerPerformance> findByPerformanceIdAndPerformerId(
            Long performanceId, Long performerId);
}
