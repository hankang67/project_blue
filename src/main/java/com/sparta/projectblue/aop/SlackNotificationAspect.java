package com.sparta.projectblue.aop;

import com.sparta.projectblue.domain.common.enums.ReservationStatus;
import com.sparta.projectblue.domain.hall.entity.Hall;
import com.sparta.projectblue.domain.hall.repository.HallRepository;
import com.sparta.projectblue.domain.performance.entity.Performance;
import com.sparta.projectblue.domain.performance.repository.PerformanceRepository;
import com.sparta.projectblue.domain.reservation.dto.CreateReservationResponseDto;
import com.sparta.projectblue.domain.reservation.dto.DeleteReservationRequestDto;
import com.sparta.projectblue.domain.reservation.entity.Reservation;
import com.sparta.projectblue.domain.reservation.repository.ReservationRepository;
import com.sparta.projectblue.domain.user.entity.User;
import com.sparta.projectblue.domain.user.repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Aspect
@Component
@RequiredArgsConstructor
public class SlackNotificationAspect {

    private final SlackNotifier slackNotifier;

    private final HallRepository hallRepository;
    private final PerformanceRepository performanceRepository;
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final JavaMailSender javaMailSender;

    private final static String SENDER_EMAIL = "${MAIL_USERNAME}";

    @Pointcut(
            "execution(* com.sparta.projectblue.domain.reservation.service.ReservationService.create(..))")
    public void createMessagePointcut() {}

    @Pointcut(
            "execution(* com.sparta.projectblue.domain.reservation.service.ReservationService.delete(..))")
    public void deleteMessagePointcut() {}

    @AfterReturning(pointcut = "createMessagePointcut()", returning = "result")
    public void sendMessage(JoinPoint joinPoint, CreateReservationResponseDto result) {

        Long userId = (Long) joinPoint.getArgs()[0];
        User user = userRepository.findById(userId).orElseThrow();
        String username = user.getName();

        // 예약 정보를 가져오기 위해 ID를 사용
        Long reservationId = result.getId();

        // 예약 정보 조회
        Reservation reservation =
                reservationRepository
                        .findById(reservationId)
                        .orElseThrow(() -> new IllegalArgumentException("예약정보를 찾을 수 없습니다."));

        // 공연 정보 조회
        Performance performance =
                performanceRepository
                        .findById(reservation.getPerformanceId())
                        .orElseThrow(() -> new IllegalArgumentException("공연을 찾을 수 없습니다."));

        // 공연장소 정보 조회
        Hall hall =
                hallRepository
                        .findById(performance.getHallId())
                        .orElseThrow(() -> new IllegalArgumentException("공연장을 찾을 수 없습니다."));

        if (result.getStatus().equals(ReservationStatus.PENDING)) {
            String title = "[티켓_예매완료]";
            String message =
                    String.format(
                            " %s 고객님, %s 공연이 %s 공연장 %s좌석 으로 예약되었습니다.",
                            username, performance.getTitle(), hall.getName(), result.getSeats());

            slackNotifier.sendMessage(title, message);
        }

        // MAIL 발송
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            String body = "";
            body += "<h1> " + user.getName() + " 님의 예매 내역입니다. </h1>";
            body += "<h3> 이름 : " + result.getPerformanceTitle() + " </h3>";
            body += "<h3> 일시 : " + result.getRoundDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + " </h3>";
            body += "<h3> 좌석 : " + result.getSeats() + " </h3>";
            body += "<h3> 총 가격 : " + result.getPrice() + " </h3>";

            MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");
            helper.setFrom(SENDER_EMAIL); // 보내는 사람
            helper.setTo(user.getEmail()); // 받는 사람
            helper.setSubject(username + "님의 예매 내역"); // 이메일 제목
            helper.setText(body, true);
        } catch (MessagingException e){
            System.out.println(e);
        }

        javaMailSender.send(message);

    }

    @AfterReturning(pointcut = "deleteMessagePointcut()")
    public void deleteSend(JoinPoint joinPoint) {

        DeleteReservationRequestDto request = (DeleteReservationRequestDto) joinPoint.getArgs()[1];

        // 예약 정보를 가져오기 위해 ID를 사용
        Long reservationId = request.getReservationId();

        // 예약 정보 조회
        Reservation reservation =
                reservationRepository
                        .findById(reservationId)
                        .orElseThrow(() -> new IllegalArgumentException("예약정보를 찾을 수 없습니다."));

        // 공연 정보 조회
        Performance performance =
                performanceRepository
                        .findById(reservation.getPerformanceId())
                        .orElseThrow(() -> new IllegalArgumentException("공연을 찾을 수 없습니다."));

        // 사용자 정보 조회
        User user =
                userRepository
                        .findById(reservation.getUserId())
                        .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        if (reservation.getStatus().equals(ReservationStatus.CANCELED)) {
            String title = "[티켓_예매취소완료]";
            String message =
                    String.format(
                            "%s 고객님, %s 공연의 예약이 취소 되었습니다.", user.getName(), performance.getTitle());

            System.out.println("현재 상태: CANCELED");

            slackNotifier.sendMessage(title, message);
        }
    }
}
