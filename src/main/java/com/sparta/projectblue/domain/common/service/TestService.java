package com.sparta.projectblue.domain.common.service;

import com.sparta.projectblue.domain.common.enums.Category;
import com.sparta.projectblue.domain.common.enums.PerformanceStatus;

import com.sparta.projectblue.domain.common.enums.ReservationStatus;
import com.sparta.projectblue.domain.common.enums.UserRole;
import com.sparta.projectblue.domain.hall.entity.Hall;
import com.sparta.projectblue.domain.hall.repository.HallRepository;
import com.sparta.projectblue.domain.payment.entity.Payment;
import com.sparta.projectblue.domain.payment.repository.PaymentRepository;
import com.sparta.projectblue.domain.performance.entity.Performance;
import com.sparta.projectblue.domain.performance.repository.PerformanceRepository;
import com.sparta.projectblue.domain.performer.entity.Performer;
import com.sparta.projectblue.domain.performer.repository.PerformerRepository;
import com.sparta.projectblue.domain.performerperformance.entity.PerformerPerformance;
import com.sparta.projectblue.domain.performerperformance.repository.PerformerPerformanceRepository;
import com.sparta.projectblue.domain.poster.entity.Poster;
import com.sparta.projectblue.domain.poster.repository.PosterRepository;
import com.sparta.projectblue.domain.reservation.entity.Reservation;
import com.sparta.projectblue.domain.reservation.repository.ReservationRepository;
import com.sparta.projectblue.domain.round.entity.Round;
import com.sparta.projectblue.domain.round.repository.RoundRepository;
import com.sparta.projectblue.domain.user.entity.User;
import com.sparta.projectblue.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class TestService {

    private final UserRepository userRepository;
    private final HallRepository hallRepository;
    private final PerformanceRepository performanceRepository;
    private final PosterRepository posterRepository;
    private final PerformerRepository performerRepository;
    private final PerformerPerformanceRepository performerPerformanceRepository;
    private final RoundRepository roundRepository;
    private final ReservationRepository reservationRepository;
    private final PaymentRepository paymentRepository;
    private final PasswordEncoder passwordEncoder;

    public void test() {
        // 사용자
        IntStream.range(0, 10).forEach(i -> {
            User user = new User( "user" + i + "@example.com","User" + i, passwordEncoder.encode("abc123?!"), UserRole.ROLE_USER);
            userRepository.save(user);
        });

        // 공연장
        IntStream.range(0, 10).forEach(i -> {
            Hall hall = new Hall("Hall" + i, "Address" + i, 100 + i);
            hallRepository.save(hall);
        });

        // 공연
        IntStream.range(0, 10).forEach(i -> {
            Category category = Category.values()[i % Category.values().length];
            Performance performance = new Performance(1L, "Performance" + i, LocalDateTime.now(), LocalDateTime.now().plusDays(10), 5000 + i * 100, category, "Description" + i, 120);
            performanceRepository.save(performance);
        });

        // 포스터
        IntStream.range(0, 10).forEach(i -> {
            Poster poster = new Poster((long) (i + 1), "Poster" + i, "https://example.com/poster" + i);
            posterRepository.save(poster);
        });

        // 출연자
        IntStream.range(0, 10).forEach(i -> {
            Performer performer = new Performer("Performer" + i, "1990-01-01", "Nation" + i);
            performerRepository.save(performer);
        });

        // 출연자-공연 중간테이블
        IntStream.range(0, 10).forEach(i -> {
            PerformerPerformance performerPerformance = new PerformerPerformance((long) (i + 1), (long) (i + 1));
            performerPerformanceRepository.save(performerPerformance);
        });


        // 회차
        IntStream.range(0, 10).forEach(i -> {
            PerformanceStatus status = PerformanceStatus.values()[i % PerformanceStatus.values().length];
            Round round = new Round(1L, LocalDateTime.now().plusDays(i), status);
            roundRepository.save(round);
        });


        // 예매
        IntStream.range(0, 10).forEach(i -> {
            ReservationStatus status = ReservationStatus.values()[i % ReservationStatus.values().length]; // 순환해서 상태 선택
            Reservation reservation = new Reservation(1L, 1L, 1L, status, 5000 + i * 100);  // 생성자에 맞게 수정
            reservationRepository.save(reservation);
        });


        // 결제
        IntStream.range(0, 10).forEach(i -> {
            Payment payment = new Payment("Card", "TXID" + i, "Credit Card", 5000 + i * 100, LocalDateTime.now());
            paymentRepository.save(payment);
        });
    }
}
