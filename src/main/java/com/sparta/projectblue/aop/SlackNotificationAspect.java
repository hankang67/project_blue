package com.sparta.projectblue.aop;

import com.sparta.projectblue.domain.common.enums.ReservationStatus;
import com.sparta.projectblue.domain.hall.entity.Hall;
import com.sparta.projectblue.domain.hall.repository.HallRepository;
import com.sparta.projectblue.domain.performance.entity.Performance;
import com.sparta.projectblue.domain.performance.repository.PerformanceRepository;
import com.sparta.projectblue.domain.reservation.dto.CreateReservationDto;
import com.sparta.projectblue.domain.reservation.entity.Reservation;
import com.sparta.projectblue.domain.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
@RequiredArgsConstructor
public class SlackNotificationAspect {

    private final SlackNotifier slackNotifier;

    private final HallRepository hallRepository;
    private final PerformanceRepository performanceRepository;
    private final ReservationRepository reservationRepository;

    @Pointcut("execution(* com.sparta.projectblue.domain.reservation.service.ReservationService.create(..))")
    public void sendMessagePointcut() {
    }

    @AfterReturning(pointcut = "sendMessagePointcut()", returning = "result")
    public void sendMessage(CreateReservationDto.Response result) {

        // 예약 정보를 가져오기 위해 ID를 사용
        Long reservationId = result.getId();

        // 예약 정보 조회
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));

        // 공연 정보 조회
        Performance performance = performanceRepository.findById(reservation.getPerformanceId())
                .orElseThrow(() -> new IllegalArgumentException("Performance not found"));

        // 공연장소 정보 조회
        Hall hall = hallRepository.findById(performance.getHallId())
                .orElseThrow(() -> new IllegalArgumentException("Hall not found"));


        if (result.getStatus().equals(ReservationStatus.PENDING)) {
            String title = "티켓_예매완료";
            String message = String.format("고객님, '%s' 공연이 '%s' 공연장으로 예약되었습니다.",
                    performance.getTitle(), hall.getName());

            slackNotifier.sendMessage(title, message);
        }
    }
}
