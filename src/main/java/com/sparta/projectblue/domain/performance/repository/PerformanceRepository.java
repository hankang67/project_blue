package com.sparta.projectblue.domain.performance.repository;

import com.sparta.projectblue.domain.performance.entity.Performance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PerformanceRepository extends JpaRepository<Performance, Long>, PerformanceQueryRepository {
    List<Performance> findAllById(Long performanceId);
}