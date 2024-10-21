package com.sparta.projectblue.domain.performer.repository;

import com.sparta.projectblue.domain.performer.entity.Performer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformerRepository extends JpaRepository<Performer, Long> {
}
