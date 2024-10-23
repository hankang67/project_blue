package com.sparta.projectblue.domain.hall.repository;

import com.sparta.projectblue.domain.hall.entity.Hall;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HallRepository extends JpaRepository<Hall, Long> {
}
