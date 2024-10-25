//package com.sparta.projectblue.aop;
//
//import com.sparta.projectblue.domain.hall.entity.Hall;
//import com.sparta.projectblue.domain.hall.repository.HallRepository;
//import com.sparta.projectblue.domain.performance.entity.Performance;
//import com.sparta.projectblue.domain.performance.repository.PerformanceRepository;
//import com.sparta.projectblue.domain.reservation.dto.CreateReservationDto;
//import com.sparta.projectblue.domain.reservation.entity.Reservation;
//import com.sparta.projectblue.domain.reservation.repository.ReservationRepository;
//import com.sparta.projectblue.domain.user.entity.User;
//import com.sparta.projectblue.domain.user.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.ApplicationEventPublisher;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@RequiredArgsConstructor
//public class NotificationService {
//
//    //이벤트를 발행을 위해 주입
//    private final ApplicationEventPublisher eventPublisher;
//
//    private final HallRepository hallRepository;
//    private final PerformanceRepository performanceRepository;
//    private final ReservationRepository reservationRepository;
//    private final UserRepository userRepository;
//
//    @Transactional
//    public void create (CreateReservationDto.Response response){
//
//        // 예약 정보를 가져오기 위해 ID를 사용
//        Long reservationId = response.getId();
//
//        // 예약 정보 조회
//        Reservation reservation = reservationRepository.findById(reservationId)
//                .orElseThrow(() -> new IllegalArgumentException("예약정보를 찾을 수 없습니다."));
//
//        // 공연 정보 조회
//        Performance performance = performanceRepository.findById(reservation.getPerformanceId())
//                .orElseThrow(() -> new IllegalArgumentException("공연을 찾을 수 없습니다."));
//
//        // 공연장소 정보 조회
//        Hall hall = hallRepository.findById(performance.getHallId())
//                .orElseThrow(() -> new IllegalArgumentException("공연장을 찾을 수 없습니다."));
//
//        // 사용자 정보 조회
//        User user = userRepository.findById(reservation.getUserId())
//                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
//
//        //  NotificationEvent에 각 정보를 담아 이벤트 발행
//        NotificationEvent event = new NotificationEvent(reservationId, reservation.getStatus(),
//                performance.getTitle(), user.getName(), hall.getName());
//        System.out.println("이벤트" +event);
//        eventPublisher.publishEvent(event);
//    }
//}
