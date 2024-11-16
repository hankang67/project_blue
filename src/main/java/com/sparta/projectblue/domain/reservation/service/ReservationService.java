package com.sparta.projectblue.domain.reservation.service;

import com.sparta.projectblue.domain.common.dto.AuthUser;
import com.sparta.projectblue.domain.common.enums.PaymentStatus;
import com.sparta.projectblue.domain.common.enums.PerformanceStatus;
import com.sparta.projectblue.domain.common.enums.ReservationStatus;
import com.sparta.projectblue.domain.email.service.EmailCreateService;
import com.sparta.projectblue.domain.hall.entity.Hall;
import com.sparta.projectblue.domain.hall.repository.HallRepository;
import com.sparta.projectblue.domain.payment.entity.Payment;
import com.sparta.projectblue.domain.payment.repository.PaymentRepository;
import com.sparta.projectblue.domain.payment.service.PaymentService;
import com.sparta.projectblue.domain.performance.entity.Performance;
import com.sparta.projectblue.domain.performance.repository.PerformanceRepository;
import com.sparta.projectblue.domain.reservation.dto.*;
import com.sparta.projectblue.domain.reservation.entity.Reservation;
import com.sparta.projectblue.domain.reservation.repository.ReservationRepository;
import com.sparta.projectblue.domain.reservedSeat.entity.ReservedSeat;
import com.sparta.projectblue.domain.reservedSeat.repository.ReservedSeatRepository;
import com.sparta.projectblue.domain.review.entity.Review;
import com.sparta.projectblue.domain.review.repository.ReviewRepository;
import com.sparta.projectblue.domain.round.entity.Round;
import com.sparta.projectblue.domain.round.repository.RoundRepository;
import com.sparta.projectblue.domain.user.entity.User;
import com.sparta.projectblue.domain.user.repository.UserRepository;
import com.sparta.projectblue.sse.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationService {

    private final JdbcTemplate jdbcTemplate;

    private final ReservationRepository reservationRepository;

    private final HallRepository hallRepository;
    private final PaymentRepository paymentRepository;
    private final PerformanceRepository performanceRepository;
    private final ReservedSeatRepository reservedSeatRepository;
    private final ReviewRepository reviewRepository;
    private final RoundRepository roundRepository;
    private final UserRepository userRepository;
    private final EmailCreateService emailCreateService;
    private final NotificationService notificationService;

    private final PaymentService paymentService;

    private final PasswordEncoder passwordEncoder;

    @Transactional
//    @DistributedLock(key = "#reservationId")
    public CreateReservationResponseDto create(Long id, CreateReservationRequestDto request) {

        // 회차 가져옴 (예매상태확인)
        Round round =
                roundRepository
                        .findById(request.getRoundId())
                        .orElseThrow(() -> new IllegalArgumentException("round not found"));

        // 공연 가져옴 (이름 뽑아야함)
        Performance performance =
                performanceRepository
                        .findById(round.getPerformanceId())
                        .orElseThrow(() -> new IllegalArgumentException("performance not found"));

        // 오픈전
        if (round.getStatus().equals(PerformanceStatus.BEFORE_OPEN)) {
            throw new IllegalArgumentException("Reservation not yet open.");
        }

        // 매진
        if (round.getStatus().equals(PerformanceStatus.SOLD_OUT)) {
            throw new IllegalArgumentException("Sold out");
        }

        // 공연장 가져옴 (좌석수 확인용)
        Hall hall =
                hallRepository
                        .findById(performance.getHallId())
                        .orElseThrow(() -> new IllegalArgumentException("hall not found"));

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
            if (reservedSeatRepository
                    .findByRoundIdAndSeatNumber(request.getRoundId(), i)
                    .isPresent()) {
                throw new IllegalArgumentException("ReservedSeat already reserved");
            }
        }

        // 가격정보 가져옴
        Long price = performance.getPrice() * request.getSeats().size();

        // 예약 생성
        Reservation newReservation =
                new Reservation(
                        id,
                        round.getPerformanceId(),
                        request.getRoundId(),
                        ReservationStatus.PENDING,
                        price);

        reservationRepository.save(newReservation);


        for (Integer i : request.getSeats()) {
            reservedSeatRepository.save(
                    new ReservedSeat(newReservation.getId(), newReservation.getRoundId(), i));
        }

        CreateReservationResponseDto responseDto =
                new CreateReservationResponseDto(
                        newReservation.getId(),
                        performance.getTitle(),
                        round.getDate(),
                        request.getSeats(),
                        newReservation.getPrice(),
                        ReservationStatus.PENDING);


        emailCreateService.sendReservationEmail(id, responseDto);

        // 예매 성공 알림 (SSE 전송)
        String title = "[티켓 예매 완료]";
        String message = String.format(
                "%s 고객님, 예매가 완료되었습니다.\n" +
                        "상품정보: %s 공연, %s 회차, %s 공연장, %s 좌석\n" +
                        "일시: %s로 예약되었습니다.",
                id, performance.getTitle(), request.getRoundId(), hall.getName(), request.getSeats(),
                round.getDate());

        notificationService.notify(String.valueOf(id), title, message);

        return responseDto;
    }

//    @Transactional
//    public void delete(Long id, DeleteReservationRequestDto request) throws Exception {
//        // 예매내역 정보 조회
//        Long reservationId;
//        Long reservationUserId;
//        Long performanceId;
//        ReservationStatus status;
//        Long paymentId;
//
//        try {
//            Map<String, Object> reservationMap = jdbcTemplate.queryForMap(
//                    "SELECT id, user_id, performance_id, status, payment_id FROM reservations WHERE id = ?",
//                    request.getReservationId()
//            );
//            reservationId = (Long) reservationMap.get("id");
//            reservationUserId = (Long) reservationMap.get("user_id");
//            performanceId = (Long) reservationMap.get("performance_id");
//            status = ReservationStatus.valueOf((String) reservationMap.get("status"));
//            paymentId = (Long) reservationMap.get("payment_id");
//        } catch (EmptyResultDataAccessException e) {
//            throw new IllegalArgumentException("Reservation not found");
//        }
//
//        // 사용자 가져오기
//        String userName;
//        String userPassword;
//        try {
//            Map<String, Object> userMap = jdbcTemplate.queryForMap(
//                    "SELECT id, name, password FROM users WHERE id = ?",
//                    id
//            );
//            userName = (String) userMap.get("name");
//            userPassword = (String) userMap.get("password");
//        } catch (EmptyResultDataAccessException e) {
//            throw new IllegalArgumentException("User not found");
//        }
//
//        // 공연 정보 조회
//        String performanceTitle;
//        try {
//            performanceTitle = jdbcTemplate.queryForObject(
//                    "SELECT title FROM performances WHERE id = ?",
//                    new Object[]{performanceId},
//                    String.class
//            );
//        } catch (EmptyResultDataAccessException e) {
//            throw new IllegalArgumentException("Performance not found");
//        }
//
//        // 사용자 권한 확인
//        if (!reservationUserId.equals(id)) {
//            throw new IllegalArgumentException("예매자가 아닙니다");
//        }
//
//        // 계정 비밀번호 확인
//        if (!passwordEncoder.matches(request.getPassword(), userPassword)) {
//            throw new IllegalArgumentException("Incorrect password");
//        }
//
//        // 취소 상태 확인
//        if (status.equals(ReservationStatus.CANCELED)) {
//            throw new IllegalArgumentException("Reservation already cancelled.");
//        }
//
//        // 예약 좌석 삭제
//        jdbcTemplate.update("DELETE FROM reserved_seats WHERE reservation_id = ?", reservationId);
//
//        // 결제 취소 여부 확인 및 결제 취소
//        if (Objects.nonNull(paymentId)) {
//            String paymentKey;
//            PaymentStatus paymentStatus;
//
//            try {
//                Map<String, Object> paymentMap = jdbcTemplate.queryForMap(
//                        "SELECT payment_key, status FROM payments WHERE id = ?",
//                        paymentId
//                );
//                paymentKey = (String) paymentMap.get("payment_key");
//                paymentStatus = PaymentStatus.valueOf((String) paymentMap.get("status"));
//            } catch (EmptyResultDataAccessException e) {
//                throw new IllegalArgumentException("Payment not found");
//            }
//
//            if (!paymentStatus.equals(PaymentStatus.CANCELED)) {
//                paymentService.cancelPayment(paymentKey, "예매를 취소합니다");
//            }
//        }
//
//        // 알림 전송
//        String title = "[티켓_예매취소완료]";
//        String message = String.format("%s 고객님, %s 공연의 예약이 취소 되었습니다.", userName, performanceTitle);
//        notificationService.notify(userName, title, message);
//
//        // 예약 상태 업데이트 (취소)
//        jdbcTemplate.update(
//                "UPDATE reservations SET status = ? WHERE id = ?",
//                ReservationStatus.CANCELED.name(),
//                reservationId
//        );
//    }

    @Transactional
    public void delete(Long id, DeleteReservationRequestDto request) throws Exception {

        // 예매내역이 있는지 확인
        Reservation reservation =
                reservationRepository
                        .findById(request.getReservationId())
                        .orElseThrow(() -> new IllegalArgumentException("reservation not found"));

        // 사용자 가져옴
        User user =
                userRepository
                        .findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // 공연 정보 조회
        Performance performance =
                performanceRepository
                        .findById(reservation.getPerformanceId())
                        .orElseThrow(() -> new IllegalArgumentException("performance not found"));

        if(!reservation.getUserId().equals(user.getId())) {
            throw new IllegalArgumentException("예매자가 아닙니다");
        }

        // 계정 비밀번호 확인
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Incorrect password");
        }

        // 이미 취소 되었는지 확인
        if (reservation.getStatus().equals(ReservationStatus.CANCELED)) {
            throw new IllegalArgumentException("Reservation already cancelled.");
        }

        List<ReservedSeat> reservedSeats =
                reservedSeatRepository.findByReservationId(reservation.getId());

        if (reservedSeats.isEmpty()) {
            throw new IllegalArgumentException("ReservedSeat does not exist");
        }

        reservedSeatRepository.deleteAll(reservedSeats);

        if (Objects.nonNull(reservation.getPaymentId())) {
            Payment payment =
                    paymentRepository
                            .findById(reservation.getPaymentId())
                            .orElseThrow(() -> new IllegalArgumentException("payment not found"));

            if (!payment.getStatus().equals(PaymentStatus.CANCELED)) {
                paymentService.cancelPayment(payment.getPaymentKey(), "예매를 취소합니다");
            }
        }

        String title = "[티켓_예매취소완료]";
            String message =
                    String.format(
                            "%s 고객님, %s 공연의 예약이 취소 되었습니다.", user.getName(), performance.getTitle());
            notificationService.notify(user.getName(), title, message);

        reservation.resCanceled();
    }

    public GetReservationResponseDto getReservation(AuthUser user, Long reservationId) {

        Reservation reservation =
                reservationRepository
                        .findById(reservationId)
                        .orElseThrow(() -> new IllegalArgumentException("예약 내역을 찾을 수 없습니다"));

        if (!reservation.getUserId().equals(user.getId())) {
            throw new IllegalArgumentException("예매자가 아닙니다");
        }

        Performance performance =
                performanceRepository
                        .findById(reservation.getPerformanceId())
                        .orElseThrow(() -> new IllegalArgumentException("공연을 찾을 수 없습니다."));

        Round round =
                roundRepository
                        .findById(reservation.getRoundId())
                        .orElseThrow(() -> new IllegalArgumentException("공연 회차를 찾을 수 없습니다"));

        List<ReservedSeat> reservedSeats =
                reservedSeatRepository.findByReservationId(reservation.getId());

        if (reservedSeats.isEmpty()) {
            throw new IllegalArgumentException("ReservedSeat does not exist");
        }

        List<Integer> seats =
                reservedSeats.stream()
                        .map(ReservedSeat::getSeatNumber)
                        .collect(Collectors.toList());

        Payment payment = null;
        if (Objects.nonNull(reservation.getPaymentId())) {
            payment =
                    paymentRepository
                            .findById(reservation.getPaymentId())
                            .orElseThrow(() -> new IllegalArgumentException("결제 정보를 찾을 수 없습니다"));
        }

        Review review = reviewRepository.findByReservationId(reservation.getId()).orElse(null);

        return new GetReservationResponseDto(
                performance, round.getDate(), reservation, user.getName(), seats, payment, review);
    }

    public Page<GetReservationsResponseDto> getReservations(Long userId, int page, int size) {

        // 페이지 요청 설정
        PageRequest pageRequest = PageRequest.of(page, size);

        // 예약 가져오기
        Page<Reservation> reservationsPage = reservationRepository.findByUserId(userId, pageRequest);

        // DTO 리스트 생성
        List<GetReservationsResponseDto> responseList = new ArrayList<>();

        for (Reservation reservation : reservationsPage) {
            Performance performance = performanceRepository
                    .findById(reservation.getPerformanceId())
                    .orElseThrow(() -> new IllegalArgumentException("Performance not found for reservation"));

            List<ReservedSeat> seats = reservedSeatRepository.findByReservationId(reservation.getId());

            Hall hall = hallRepository
                    .findById(performance.getHallId())
                    .orElseThrow(() -> new IllegalArgumentException("공연장을 찾을 수 없습니다"));

            Round round = roundRepository
                    .findById(reservation.getRoundId())
                    .orElseThrow(() -> new IllegalArgumentException("공연 회차를 찾을 수 없습니다"));

            responseList.add(new GetReservationsResponseDto(
                    performance.getTitle(),
                    seats.size(),
                    reservation.getId(),
                    reservation.getCreatedAt(),
                    hall.getName(),
                    round.getDate(),
                    reservation.getStatus()
            ));
        }

        // 페이지 변환 후 반환
        return new PageImpl<>(responseList, pageRequest, reservationsPage.getTotalElements());
    }

//    @Async("mailExecutor")
//    public Future<Page<GetReservationsResponseDto>> getReservations(Long userId, int page, int size) {
//
//        // 페이지 요청 설정
//        PageRequest pageRequest = PageRequest.of(page, size);
//
//        // 예약 가져오기
//        Page<Reservation> reservationsPage = reservationRepository.findByUserId(userId, pageRequest);
//
//        // DTO 리스트 생성
//        List<GetReservationsResponseDto> responseList = new ArrayList<>();
//
//        for (Reservation reservation : reservationsPage) {
//            Performance performance = performanceRepository
//                    .findById(reservation.getPerformanceId())
//                    .orElseThrow(() -> new IllegalArgumentException("Performance not found for reservation"));
//
//            List<ReservedSeat> seats = reservedSeatRepository.findByReservationId(reservation.getId());
//
//            Hall hall = hallRepository
//                    .findById(performance.getHallId())
//                    .orElseThrow(() -> new IllegalArgumentException("공연장을 찾을 수 없습니다"));
//
//            Round round = roundRepository
//                    .findById(reservation.getRoundId())
//                    .orElseThrow(() -> new IllegalArgumentException("공연 회차를 찾을 수 없습니다"));
//
//            responseList.add(new GetReservationsResponseDto(
//                    performance.getTitle(),
//                    seats.size(),
//                    reservation.getId(),
//                    reservation.getCreatedAt(),
//                    hall.getName(),
//                    round.getDate(),
//                    reservation.getStatus()
//            ));
//        }
//
//        // 페이지 변환 후 반환
//        Page<GetReservationsResponseDto> responsePage = new PageImpl<>(responseList, pageRequest, reservationsPage.getTotalElements());
//        return new AsyncResult<>(responsePage);
//    }

}
