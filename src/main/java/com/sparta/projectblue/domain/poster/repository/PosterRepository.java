package com.sparta.projectblue.domain.poster.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sparta.projectblue.domain.poster.entity.Poster;

public interface PosterRepository extends JpaRepository<Poster, Long> {

    Optional<Poster> findByPerformanceId(Long performanceId);
}
