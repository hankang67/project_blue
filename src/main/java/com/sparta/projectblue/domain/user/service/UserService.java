package com.sparta.projectblue.domain.user.service;

import com.sparta.projectblue.domain.common.dto.AuthUser;
import com.sparta.projectblue.domain.payment.entity.Payment;
import com.sparta.projectblue.domain.payment.repository.PaymentRepository;
import com.sparta.projectblue.domain.performance.entity.Performance;
import com.sparta.projectblue.domain.performance.repository.PerformanceRepository;
import com.sparta.projectblue.domain.reservation.entity.Reservation;
import com.sparta.projectblue.domain.reservation.repository.ReservationRepository;
import com.sparta.projectblue.domain.reservedSeat.entity.ReservedSeat;
import com.sparta.projectblue.domain.reservedSeat.repository.ReservedSeatRepository;
import com.sparta.projectblue.domain.user.dto.DeleteUserDto;
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
public class UserService {

    private final UserRepository userRepository;

    private final ReservationRepository reservationRepository;
    private final PaymentRepository paymentRepository;
    private final PerformanceRepository performanceRepository;
    private final ReservedSeatRepository reservedSeatRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void deleteUser(AuthUser authUser, DeleteUserDto.Request deleteUserRequest) {
        User user =
                userRepository
                        .findById(authUser.getId())
                        .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        if (user.isDeleted()) {
            throw new IllegalArgumentException("이미 탈퇴한 유저입니다.");
        }

        if (!passwordEncoder.matches(deleteUserRequest.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        user.userDeleted();
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

    //예매 내역 상세 조회
    public ReservationDetailDto.Response getReservationDetail(Long userId, Long reservationId) {
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
                    .orElseThrow(() -> new IllegalArgumentException("Payment not found for reservation"));
            payDto = new ReservationDetailDto.PaymentDto(payment);
        }

        // Create DTOs
        ReservationDetailDto.PerformanceDto perDto = new ReservationDetailDto.PerformanceDto(performance);

        List<ReservedSeat> reservedSeats = reservedSeatRepository.findByReservationId(reservation.getId());

        if(reservedSeats.isEmpty()) {
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
}

