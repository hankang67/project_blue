//package com.sparta.projectblue.aop;
//
//import com.sparta.projectblue.domain.common.enums.ReservationStatus;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.event.EventListener;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class NotificationEventHandler {
//
//    private final SlackNotifier slackNotifier;
//
//    @EventListener
//    public void handleEvent(NotificationEvent event) {
//
//        // 이벤트 겍체에서 정보 가져오기
//        Long reservationId = event.getReservationId();
//        ReservationStatus status = event.getStatus();
//        String userName = event.getUserName();
//        String performanceTitle = event.getPerformanceTitle();
//        String hallName = event.getHallName();
//
//        if (status.equals(ReservationStatus.PENDING)) {
//            String title = "[티켓_예매완료]";
//            String message = String.format("'%s' 고객님, '%s' 공연이 '%s' 공연장으로 예약되었습니다.",
//                    userName, performanceTitle, hallName);
//            slackNotifier.sendMessage(title, message);
//            System.out.println("이벤트" +event);
//        } else if (status.equals(ReservationStatus.CANCELED)) {
//            String title = "[티켓_예매취소완료]";
//            String message = String.format("'%s' 고객님, '%s' 공연의 예약이 취소되었습니다.",
//                    userName, performanceTitle);
//            System.out.println("현재 상태: " + status.getStatus());
//            slackNotifier.sendMessage(title, message);
//        }
//    }
//}
