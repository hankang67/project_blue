package com.sparta.projectblue.domain.performerperformance.repository;

import com.sparta.projectblue.domain.performerperformance.entity.PerformerPerformance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PerformerPerformanceRepository extends JpaRepository<PerformerPerformance, Long> {
    List<PerformerPerformance> findAllByPerformanceId(Long performanceId);
}
