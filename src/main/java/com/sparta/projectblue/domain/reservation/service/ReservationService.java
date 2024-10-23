package com.sparta.projectblue.domain.reservation.service;

import com.sparta.projectblue.domain.common.enums.PerformanceStatus;
import com.sparta.projectblue.domain.common.enums.ReservationStatus;
import com.sparta.projectblue.domain.hall.entity.Hall;
import com.sparta.projectblue.domain.hall.repository.HallRepository;
import com.sparta.projectblue.domain.payment.entity.Payment;
import com.sparta.projectblue.domain.payment.repository.PaymentRepository;
import com.sparta.projectblue.domain.performance.entity.Performance;
import com.sparta.projectblue.domain.performance.repository.PerformanceRepository;
import com.sparta.projectblue.domain.reservation.dto.CreateReservationDto;
import com.sparta.projectblue.domain.reservation.dto.DeleteReservationDto;
import com.sparta.projectblue.domain.reservation.entity.Reservation;
import com.sparta.projectblue.domain.reservation.repository.ReservationRepository;
import com.sparta.projectblue.domain.reservedSeat.entity.ReservedSeat;
import com.sparta.projectblue.domain.reservedSeat.repository.ReservedSeatRepository;
import com.sparta.projectblue.domain.round.entity.Round;
import com.sparta.projectblue.domain.round.repository.RoundRepository;
import com.sparta.projectblue.domain.user.dto.ReservationDetailDto;
import com.sparta.projectblue.domain.user.dto.ReservationDto;
import com.sparta.projectblue.domain.user.entity.User;
import com.sparta.projectblue.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationService {

    private final ReservationRepository reservationRepository;

    private final HallRepository hallRepository;
    private final PaymentRepository paymentRepository;
    private final PerformanceRepository performanceRepository;
    private final ReservedSeatRepository reservedSeatRepository;
    private final RoundRepository roundRepository;
    private final UserRepository userRepository;

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
        Hall hall = hallRepository.findById(performance.getHallId()).orElseThrow(() ->
                new IllegalArgumentException("hallo not found"));

        // 예매 가능 티켓 매수 제한
        if (request.getSeats().size() > 4) {
            throw new IllegalArgumentException("Maximum seats 4");
        }

        // 좌석 검증
        for (Integer i : request.getSeats()) {
            // 유효한 좌석번호인지 확인
            if (i > hall.getSeats()) {
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

        for (Integer i : request.getSeats()) {
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
        Reservation reservation = reservationRepository.findById(request.getReservationId()).orElseThrow(() ->
                new IllegalArgumentException("reservation not found"));

        // 이미 취소 되었는지 확인
        if (reservation.getStatus().equals(ReservationStatus.CANCELED)) {
            throw new IllegalArgumentException("Reservation already cancelled.");
        }

        List<ReservedSeat> reservedSeats = reservedSeatRepository.findByReservationId(reservation.getId());

        if (reservedSeats.isEmpty()) {
            throw new IllegalArgumentException("ReservedSeat does not exist");
        }

        reservedSeatRepository.deleteAll(reservedSeats);

        reservation.resCanceled();
    }

    //예매 내역 상세 조회
    public ReservationDetailDto.Response getReservation(Long userId, Long reservationId) {
        // Fetch the reservation
        Reservation reservation = reservationRepository.findByIdAndUserId(reservationId, userId);

        if (reservation == null) {
            throw new IllegalArgumentException("Reservation not found.");
        }

        // Fetch the associated performance
        Performance performance = performanceRepository.findById(reservation.getPerformanceId())
                .orElseThrow(() -> new IllegalArgumentException("Performance not found for reservation"));

        // Fetch the associated payment, if it exists (check if paymentId is not null)
        ReservationDetailDto.PaymentDto payDto = null;
        if (reservation.getPaymentId() != null) {
            Payment payment = paymentRepository.findById(reservation.getPaymentId())
//                    .orElseThrow(() -> new IllegalArgumentException("Payment not found for reservation"));
                    .orElse(null);
            payDto = new ReservationDetailDto.PaymentDto(payment);
        }

        // Create DTOs
        ReservationDetailDto.PerformanceDto perDto = new ReservationDetailDto.PerformanceDto(performance);

        List<ReservedSeat> reservedSeats = reservedSeatRepository.findByReservationId(reservation.getId());

        if (reservedSeats.isEmpty()) {
            throw new IllegalArgumentException("ReservedSeat does not exist");
        }

        List<Integer> seats = reservedSeats.stream()
                .map(ReservedSeat::getSeatNumber)
                .collect(Collectors.toList());

        // Return the response DTO
        return new ReservationDetailDto.Response(
                reservation.getId(),
                perDto,
                payDto,  // Could be null if no payment exists
                seats,
                reservation.getStatus()
        );
    }

    // 예매 내역 전체 조회
    public List<ReservationDto.Response> getReservations(Long userId) {
        // 예약 가져옴
        List<Reservation> reservations = reservationRepository.findByUserId(userId);

        if (reservations.isEmpty()) {
            throw new IllegalArgumentException("No reservations found for this user.");
        }

        List<ReservationDto.Response> responseList = new ArrayList<>();

        // Iterate through each reservation and get associated details
        for (Reservation reservation : reservations) {
            // Fetch the associated performance
            Performance performance = performanceRepository.findById(reservation.getPerformanceId())
                    .orElseThrow(() -> new IllegalArgumentException("Performance not found for reservation"));

            // Create performance DTO
            ReservationDto.PerformanceDto perDto = new ReservationDto.PerformanceDto(performance);


            // Create the response DTO for the current reservation
            ReservationDto.Response responseDto = new ReservationDto.Response(
                    reservation.getId(),
                    perDto,
                    reservation.getStatus()
            );

            // Add the response DTO to the list
            responseList.add(responseDto);
        }

        return responseList;
    }
}
