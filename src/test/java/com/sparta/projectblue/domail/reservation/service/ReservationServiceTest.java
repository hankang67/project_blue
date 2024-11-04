package com.sparta.projectblue.domail.reservation.service;

import com.sparta.projectblue.domain.common.dto.AuthUser;
import com.sparta.projectblue.domain.common.enums.Category;
import com.sparta.projectblue.domain.common.enums.PerformanceStatus;
import com.sparta.projectblue.domain.common.enums.ReservationStatus;
import com.sparta.projectblue.domain.common.enums.UserRole;
import com.sparta.projectblue.domain.hall.entity.Hall;
import com.sparta.projectblue.domain.hall.repository.HallRepository;
import com.sparta.projectblue.domain.payment.repository.PaymentRepository;
import com.sparta.projectblue.domain.payment.service.PaymentService;
import com.sparta.projectblue.domain.performance.entity.Performance;
import com.sparta.projectblue.domain.performance.repository.PerformanceRepository;
import com.sparta.projectblue.domain.reservation.dto.CreateReservationRequestDto;
import com.sparta.projectblue.domain.reservation.dto.CreateReservationResponseDto;
import com.sparta.projectblue.domain.reservation.entity.Reservation;
import com.sparta.projectblue.domain.reservation.repository.ReservationRepository;
import com.sparta.projectblue.domain.reservation.service.ReservationService;
import com.sparta.projectblue.domain.reservedSeat.entity.ReservedSeat;
import com.sparta.projectblue.domain.reservedSeat.repository.ReservedSeatRepository;
import com.sparta.projectblue.domain.review.repository.ReviewRepository;
import com.sparta.projectblue.domain.round.entity.Round;
import com.sparta.projectblue.domain.round.repository.RoundRepository;
import com.sparta.projectblue.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private HallRepository hallRepository;
    @Mock
    private PaymentRepository paymentRepository;
    @Mock
    private PerformanceRepository performanceRepository;
    @Mock
    private ReservedSeatRepository reservedSeatRepository;
    @Mock
    private ReviewRepository reviewRepository;
    @Mock
    private RoundRepository roundRepository;
    @Mock
    private UserRepository userRepository;

    @Mock
    private PaymentService paymentService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private ReservationService reservationService;

    private final AuthUser authUser = new AuthUser( 1L, "test@test.com", "testUser", UserRole.ROLE_USER);

    @Nested
    class CreateReservationTest {

        @Test
        void 예매_정상_동작() {

            // given

            List<Integer> seats = List.of(1, 2);

            String performanceTitle = "KuromiZZang";

            Long price = 50000L;

            LocalDateTime roundDate = LocalDateTime.now();

            Round round = new Round(1L, roundDate, PerformanceStatus.AVAILABLE);

            given(roundRepository.findById(anyLong())).willReturn(Optional.of(round));

            Performance performance = new Performance(1L, performanceTitle, LocalDateTime.now(), LocalDateTime.now(), price, Category.CONCERT, "description", 150);

            given(performanceRepository.findById(anyLong())).willReturn(Optional.of(performance));

            Hall hall = new Hall("Best Hall", "Address 465-648", 35000);

            given(hallRepository.findById(anyLong())).willReturn(Optional.of(hall));

            // when

            CreateReservationRequestDto request = new CreateReservationRequestDto(1L, seats);

            CreateReservationResponseDto response = reservationService.create(authUser.getId(), request);

            // then

            verify(reservationRepository, times(1)).save(any(Reservation.class));
            verify(reservedSeatRepository, times(seats.size())).save(any(ReservedSeat.class));

            assertEquals(response.getStatus(), ReservationStatus.PENDING);
            assertEquals(response.getSeats(), seats);
            assertEquals(response.getPrice(), price * seats.size());
            assertEquals(response.getPerformanceTitle(), performanceTitle);
            assertEquals(response.getRoundDate(), roundDate);
        }

        @Test
        void BEFORE_OPEN_상태의_회차_예매_오류() {

            // given

            Round round = new Round(1L, LocalDateTime.now(), PerformanceStatus.BEFORE_OPEN);

            given(roundRepository.findById(anyLong())).willReturn(Optional.of(round));

            Performance performance = new Performance(1L, "Minions", LocalDateTime.now(), LocalDateTime.now(), 156000L, Category.CONCERT, "description", 150);

            given(performanceRepository.findById(anyLong())).willReturn(Optional.of(performance));

            // when

            CreateReservationRequestDto request = new CreateReservationRequestDto(1L, List.of(1, 2));

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                    reservationService.create(authUser.getId(), request));

            // then

            assertEquals(exception.getMessage(), "Reservation not yet open.");
        }

        @Test
        void SOLD_OUT_상태의_회차_예매_오류() {

            // given

            Round round = new Round(1L, LocalDateTime.now(), PerformanceStatus.SOLD_OUT);

            given(roundRepository.findById(anyLong())).willReturn(Optional.of(round));

            Performance performance = new Performance(1L, "Minions", LocalDateTime.now(), LocalDateTime.now(), 156000L, Category.CONCERT, "description", 150);

            given(performanceRepository.findById(anyLong())).willReturn(Optional.of(performance));

            // when

            CreateReservationRequestDto request = new CreateReservationRequestDto(1L, List.of(1, 2));

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                    reservationService.create(authUser.getId(), request));

            // then

            assertEquals(exception.getMessage(), "Sold out");
        }

        @Test
        void 네개_이상의_좌석_예매_오류() {

            // given

            Round round = new Round(1L, LocalDateTime.now(), PerformanceStatus.AVAILABLE);

            given(roundRepository.findById(anyLong())).willReturn(Optional.of(round));

            Performance performance = new Performance(1L, "Minions", LocalDateTime.now(), LocalDateTime.now(), 156000L, Category.CONCERT, "description", 150);

            given(performanceRepository.findById(anyLong())).willReturn(Optional.of(performance));

            Hall hall = new Hall("Best Hall", "Address 465-648", 35000);

            given(hallRepository.findById(anyLong())).willReturn(Optional.of(hall));

            // when

            CreateReservationRequestDto request = new CreateReservationRequestDto(1L, List.of(1, 2, 5, 6, 8));

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                    reservationService.create(authUser.getId(), request));

            // then

            assertEquals(exception.getMessage(), "Maximum seats 4");
        }

        @Test
        void 공연장_좌석수_이상의_좌석번호_예매_오류() {

            // given

            Round round = new Round(1L, LocalDateTime.now(), PerformanceStatus.AVAILABLE);

            given(roundRepository.findById(anyLong())).willReturn(Optional.of(round));

            Performance performance = new Performance(1L, "Minions", LocalDateTime.now(), LocalDateTime.now(), 156000L, Category.CONCERT, "description", 150);

            given(performanceRepository.findById(anyLong())).willReturn(Optional.of(performance));

            Hall hall = new Hall("Best Hall", "Address 465-648", 10);

            given(hallRepository.findById(anyLong())).willReturn(Optional.of(hall));

            // when

            CreateReservationRequestDto request = new CreateReservationRequestDto(1L, List.of(15));

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                    reservationService.create(authUser.getId(), request));

            // then

            assertEquals(exception.getMessage(), "Invalid seat number");
        }

        @Test
        void 이선좌_예매_오류() {

            // given
            Round round = new Round(1L, LocalDateTime.now(), PerformanceStatus.AVAILABLE);

            given(roundRepository.findById(anyLong())).willReturn(Optional.of(round));

            Performance performance = new Performance(1L, "Minions", LocalDateTime.now(), LocalDateTime.now(), 156000L, Category.CONCERT, "description", 150);

            given(performanceRepository.findById(anyLong())).willReturn(Optional.of(performance));

            Hall hall = new Hall("Best Hall", "Address 465-648", 150);

            given(hallRepository.findById(anyLong())).willReturn(Optional.of(hall));

            given(reservedSeatRepository.findByRoundIdAndSeatNumber(anyLong(), eq(1)))
                    .willReturn(Optional.of(new ReservedSeat(1L, round.getId(), 1)));

            // when
            CreateReservationRequestDto request = new CreateReservationRequestDto(1L, List.of(1, 2));

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                    reservationService.create(authUser.getId(), request));

            // then
            assertEquals(exception.getMessage(), "ReservedSeat already reserved");
        }
    }

}
