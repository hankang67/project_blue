package com.sparta.projectblue.domain.reservation.repository;

import java.util.List;

public interface ReservationQueryRepository {
    List<Integer> findReservedSeats(Long roundId);

}
