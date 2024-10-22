package com.sparta.projectblue.domain.reservation.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.projectblue.domain.common.enums.ReservationStatus;
import com.sparta.projectblue.domain.reservation.entity.QReservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReservationQueryRepositoryImpl implements ReservationQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Integer> findReservedSeats(Long roundId) {
        QReservation reservation = QReservation.reservation;

        return queryFactory.select(reservation.seatNumber)
                .from(reservation)
                .where(reservation.roundId.eq(roundId)
                        .and(reservation.status.ne(ReservationStatus.CANCELED)))
                .fetch();
    }
}