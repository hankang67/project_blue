package com.sparta.projectblue.domain.reservation.repository;

import com.sparta.projectblue.domain.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
