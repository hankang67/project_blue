package com.sparta.projectblue.domain.poster.repository;

import com.sparta.projectblue.domain.poster.entity.Poster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PosterRepository extends JpaRepository<Poster, Long> {
}
