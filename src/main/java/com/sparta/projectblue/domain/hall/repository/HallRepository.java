package com.sparta.projectblue.domain.hall.repository;

import com.sparta.projectblue.domain.hall.entity.Hall;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HallRepository extends JpaRepository<Hall, Long> {

    boolean existsById(Long hallId);

    default Hall findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() ->
                new IllegalArgumentException("공연장을 찾을 수 없습니다."));
    }

}
