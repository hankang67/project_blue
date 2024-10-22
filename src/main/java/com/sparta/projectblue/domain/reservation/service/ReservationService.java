package com.sparta.projectblue.domain.reservation.service;

import com.sparta.projectblue.domain.common.enums.PerformanceStatus;
import com.sparta.projectblue.domain.common.enums.ReservationStatus;
import com.sparta.projectblue.domain.hall.entity.Hall;
import com.sparta.projectblue.domain.hall.repository.HallRepository;
import com.sparta.projectblue.domain.performance.entity.Performance;
import com.sparta.projectblue.domain.performance.repository.PerformanceRepository;
import com.sparta.projectblue.domain.reservation.dto.CreateReservationDto;
import com.sparta.projectblue.domain.reservation.entity.Reservation;
import com.sparta.projectblue.domain.reservation.repository.ReservationRepository;
import com.sparta.projectblue.domain.round.entity.Round;
import com.sparta.projectblue.domain.round.repository.RoundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationService {

    private final ReservationRepository reservationRepository;

    private final PerformanceRepository performanceRepository;
    private final RoundRepository roundRepository;
    private final HallRepository hallRepository;

    @Transactional
    public CreateReservationDto.Response create(Long id, CreateReservationDto.Request request) {

        // 회차 가져옴 (예매상태확인)
        Round round = roundRepository.findById(request.getRoundId()).orElseThrow(() ->
                new IllegalArgumentException("round not found"));

        // 공연 가져옴 (이름 뽑아야함)
        Performance performance = performanceRepository.findById(round.getPerformanceId()).orElseThrow(() ->
                new IllegalArgumentException("performance not found"));

        // 오픈전
        if (round.getStatus().equals(PerformanceStatus.BEFORE_OPEN)) {
            throw new IllegalArgumentException("Reservation not yet open.");
        }

        // 매진
        if (round.getStatus().equals(PerformanceStatus.SOLD_OUT)) {
            throw new IllegalArgumentException("Sold out");
        }

        // 공연장 가져옴 (좌석수 확인용)
        Hall hall = hallRepository.findById(performance.getHallId()).orElseThrow(()->
                new IllegalArgumentException("hallo not found"));

        // 좌석수 허용 범위 확인
        if(request.getSeatNumber() > hall.getSeats()) {
            throw new IllegalArgumentException("Invalid seat number");
        }

        // 존재하는 예약 전부 가져옴
        List<Reservation> reservations = reservationRepository.findAllByPerformanceIdAndRoundIdAndSeatNumber(
                round.getPerformanceId(), request.getRoundId(), request.getSeatNumber());

        // 전부 취소인지 확인 (하나라도 취소가 아니면 결제대기 or 결제완료라서 예약 불가능)
        boolean reservationCheck = reservations.stream()
                .anyMatch(reservation -> !reservation.getStatus().equals(ReservationStatus.CANCELED));

        if (reservationCheck) {
            throw new IllegalArgumentException("Reservation already exists.");
        }

        // 가격정보 가져옴
        int price = performance.getPrice();

        // 예약 생성
        Reservation newReservation = new Reservation(
                id,
                round.getPerformanceId(),
                request.getRoundId(),
                ReservationStatus.PENDING,
                price,
                request.getSeatNumber()
        );

        reservationRepository.save(newReservation);

        return new CreateReservationDto.Response(
                newReservation.getId(),
                performance.getTitle(),
                round.getDate(),
                newReservation.getSeatNumber(),
                newReservation.getPrice(),
                ReservationStatus.PENDING
        );
    }
}
