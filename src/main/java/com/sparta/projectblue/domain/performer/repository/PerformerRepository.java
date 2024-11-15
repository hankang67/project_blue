package com.sparta.projectblue.domain.performer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sparta.projectblue.domain.performer.entity.Performer;

public interface PerformerRepository extends JpaRepository<Performer, Long> {
    List<Performer> findAllByName(String name);
}