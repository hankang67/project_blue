package com.sparta.projectblue.domain.round.repository;

import com.sparta.projectblue.domain.round.entity.Round;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoundRepository extends JpaRepository<Round, Long> {
    List<Round> findByPerformanceId(Long performanceId);
}
