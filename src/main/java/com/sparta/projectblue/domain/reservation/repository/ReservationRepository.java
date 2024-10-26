package com.sparta.projectblue.domain.reservation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sparta.projectblue.domain.reservation.entity.Reservation;

public interface ReservationRepository
        extends JpaRepository<Reservation, Long>, ReservationQueryRepository {
    List<Reservation> findByUserId(Long userId);
}
