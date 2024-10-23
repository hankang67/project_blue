package com.sparta.projectblue.domain.reservation.service;

import com.sparta.projectblue.domain.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final UserRepository userRepository;
    private final ReservedSeatRepository reservedSeatRepository;

    private final PasswordEncoder passwordEncoder;

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

        // 예매 가능 티켓 매수 제한
        if(request.getSeats().size() > 4) {
            throw new IllegalArgumentException("Maximum seats 4");
        }

        // 좌석 검증
        for(Integer i : request.getSeats()) {
            // 유효한 좌석번호인지 확인
            if(i > hall.getSeats()) {
                throw new IllegalArgumentException("Invalid seat number");
            }

            // reservedSeats 에 좌석이 있으면 예매 불가
            if (reservedSeatRepository.findByRoundIdAndSeatNumber(request.getRoundId(), i).isPresent()) {
                throw new IllegalArgumentException("ReservedSeat already reserved");
            }
        }

        // 가격정보 가져옴
        int price = performance.getPrice() * request.getSeats().size();

        // 예약 생성
        Reservation newReservation = new Reservation(
                id,
                round.getPerformanceId(),
                request.getRoundId(),
                ReservationStatus.PENDING,
                price
        );

        reservationRepository.save(newReservation);

        for(Integer i : request.getSeats()) {
            reservedSeatRepository.save(new ReservedSeat(newReservation.getId(), newReservation.getRoundId(), i));
        }

        return new CreateReservationDto.Response(
                newReservation.getId(),
                performance.getTitle(),
                round.getDate(),
                request.getSeats(),
                newReservation.getPrice(),
                ReservationStatus.PENDING
        );
    }

    @Transactional
    public void delete(Long id, DeleteReservationDto.Request request) {
        // 사용자 가져옴
        User user =
                userRepository.findById(id).orElseThrow(() ->
                        new IllegalArgumentException("User not found"));

        // 계정 비밀번호 확인
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Incorrect password");
        }

        // 예매내역이 있는지 확인
        Reservation reservation = reservationRepository.findById(request.getReservationId()).orElseThrow(()->
                new IllegalArgumentException("reservation not found"));

        // 이미 취소 되었는지 확인
        if (reservation.getStatus().equals(ReservationStatus.CANCELED)) {
            throw new IllegalArgumentException("Reservation already cancelled.");
        }

        List<ReservedSeat> reservedSeats = reservedSeatRepository.findByReservationId(reservation.getId());

        if(reservedSeats.isEmpty()) {
            throw new IllegalArgumentException("ReservedSeat does not exist");
        }

        reservedSeatRepository.deleteAll(reservedSeats);

        reservation.resCanceled();
    }
}
