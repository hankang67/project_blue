package com.sparta.projectblue.domain.reservation.repository;

import com.sparta.projectblue.domain.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    /// 예매 내역 전체 조회
    List<Reservation> findByUserId(Long userId);

    // 예매 내역 상세 조회
    Reservation findByIdAndUserId(Long reservationId, Long userId);
}
