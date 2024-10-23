package com.sparta.projectblue.domain.poster.repository;

import com.sparta.projectblue.domain.poster.entity.Poster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PosterRepository extends JpaRepository<Poster, Long> {
    Optional<Poster> findByPerformanceId(Long performanceId);

    boolean existsByPerformanceId(Long performanceId);
}
