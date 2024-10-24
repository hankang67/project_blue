package com.sparta.projectblue.aop;

import com.sparta.projectblue.domain.common.enums.ReservationStatus;
import com.sparta.projectblue.domain.hall.entity.Hall;
import com.sparta.projectblue.domain.hall.repository.HallRepository;
import com.sparta.projectblue.domain.performance.entity.Performance;
import com.sparta.projectblue.domain.performance.repository.PerformanceRepository;
import com.sparta.projectblue.domain.reservation.dto.CreateReservationDto;
import com.sparta.projectblue.domain.reservation.dto.DeleteReservationDto;
import com.sparta.projectblue.domain.reservation.entity.Reservation;
import com.sparta.projectblue.domain.reservation.repository.ReservationRepository;
import com.sparta.projectblue.domain.user.entity.User;
import com.sparta.projectblue.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
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
    private final UserRepository userRepository;

    @Pointcut("execution(* com.sparta.projectblue.domain.reservation.service.ReservationService.create(..))")
    public void createMessagePointcut() {
    }

    @Pointcut("execution(* com.sparta.projectblue.domain.reservation.service.ReservationService.delete(..))")
    public void deleteMessagePointcut() {
    }

    @AfterReturning(pointcut = "createMessagePointcut()", returning = "result")
    public void sendMessage(CreateReservationDto.Response result) {

        // 예약 정보를 가져오기 위해 ID를 사용
        Long reservationId = result.getId();

        // 예약 정보 조회
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("예약정보를 찾을 수 없습니다."));

        // 공연 정보 조회
        Performance performance = performanceRepository.findById(reservation.getPerformanceId())
                .orElseThrow(() -> new IllegalArgumentException("공연을 찾을 수 없습니다."));

        // 공연장소 정보 조회
        Hall hall = hallRepository.findById(performance.getHallId())
                .orElseThrow(() -> new IllegalArgumentException("공연장을 찾을 수 없습니다."));

        // 사용자 정보 조회
        User user = userRepository.findById(reservation.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        if (result.getStatus().equals(ReservationStatus.PENDING)) {
            String title = "[티켓_예매완료]";
            String message = String.format( " %s 고객님, %s 공연이 %s 공연장으로 예약되었습니다.",
                   user.getName(), performance.getTitle(), hall.getName());

            slackNotifier.sendMessage(title, message);
        }
    }

    @AfterReturning(pointcut = "deleteMessagePointcut()", returning = "result")
    public void deleteSend(JoinPoint joinPoint, Object result) {
        DeleteReservationDto.Request request = (DeleteReservationDto.Request) joinPoint.getArgs()[1];

        // 예약 정보를 가져오기 위해 ID를 사용
        Long reservationId = request.getReservationId();

        // 예약 정보 조회
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("예약정보를 찾을 수 없습니다."));

        // 공연 정보 조회
        Performance performance = performanceRepository.findById(reservation.getPerformanceId())
                .orElseThrow(() -> new IllegalArgumentException("공연을 찾을 수 없습니다."));

        // 공연장소 정보 조회
        Hall hall = hallRepository.findById(performance.getHallId())
                .orElseThrow(() -> new IllegalArgumentException("공연장을 찾을 수 없습니다."));

        // 사용자 정보 조회
        User user = userRepository.findById(reservation.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));


        if (reservation.getStatus().equals(ReservationStatus.CANCELED)) {
            String title = "[티켓_예매취소완료]";
            String message = String.format("%s 고객님, %s 공연의 예약이 취소 되었습니다.",
                    user.getName(), performance.getTitle());

            System.out.println("현재 상태: CANCELED");

            slackNotifier.sendMessage(title, message);
        }
    }
}
