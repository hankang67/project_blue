//package com.sparta.projectblue.domain.user.repository;
//
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import com.sparta.projectblue.domain.hall.entity.QHall;
//import com.sparta.projectblue.domain.payment.entity.QPayment;
//import com.sparta.projectblue.domain.performance.entity.QPerformance;
//import com.sparta.projectblue.domain.reservation.entity.QReservation;
//import com.sparta.projectblue.domain.reservation.entity.Reservation;
//import com.sparta.projectblue.domain.round.entity.QRound;
//import jakarta.persistence.EntityManager;
//import lombok.RequiredArgsConstructor;
//
//import java.util.List;
//
//@RequiredArgsConstructor
//public class UserRepositoryCustomImpl implements UserRepositoryCustom {
//    private final JPAQueryFactory queryFactory;
//
////    @PersistenceContext
//    private EntityManager entityManager;
//
////    @Override
////    public List<Object[]> fetchAllReservationsByUserId(Long userId) {
////        String sql = "SELECT r.id, r.seat_number, r.status, " +
////                "p.title, p.start_date, p.price, p.category, p.description, p.duration, " +
////                "pay.payment_method, pay.payment_price, pay.payment_time " +
////                "FROM reservations r " +
////                "LEFT JOIN performances p ON r.performance_id = p.id " +
////                "LEFT JOIN payments pay ON r.payment_id = pay.id " +
////                "WHERE r.user_id = :userId";
////
////        List<Object[]> results = entityManager.createNativeQuery(sql)
////                .setParameter("userId", userId)
////                .getResultList();
////
////        return results;
////
//    @Override
//    public List<Reservation> fetchAllReservationsByUserId(Long userId) {
//        QReservation reservation = QReservation.reservation;
//        QPerformance performance = QPerformance.performance;
//        QPayment payment = QPayment.payment;
//        QHall hall = QHall.hall;
//        QRound round = QRound.round;
//
//        return queryFactory
//                .selectFrom(reservation)
//                .leftJoin(performance).on(reservation.performanceId.eq(performance.id))
//                .leftJoin(payment).on(reservation.paymentId.eq(payment.id))
//                .where(reservation.userId.eq(userId))
//                .fetch();
//    }
//
//    // 예매 상세 조회
//    @Override
////    public Reservation fetchUserWithReservation(Long reservationId) {
//////        QReservation reservation = QReservation.reservation;
////        QPerformance performance = QPerformance.performance;
////        QPayment payment = QPayment.payment;
////        QHall hall = QHall.hall;
////
////        String sql = "SELECT r.id, r.seat_number, r.status, " +
////                "p.id as performanceId, p.title, p.start_date, p.price, p.category, p.description, p.duration, " +
////                "pay.id as paymentId, pay.payment_method, pay.payment_price, pay.payment_time " +
////                "FROM reservations r " +
////                "LEFT JOIN performances p ON r.performance_id = p.id " +
////                "LEFT JOIN payments pay ON r.payment_id = pay.id " +
////                "WHERE r.id = :reservationId";
////
////        Object[] result = (Object[]) entityManager.createNativeQuery(sql)
////                .setParameter("reservationId", reservationId)
////                .getSingleResult();
////
////        Reservation reservation = new Reservation();
////
////        return reservation;
////    }
//
//    // 예매 상세 조회
//    public Reservation fetchUserWithReservation(Long reservationId) {
//
//        QReservation reservation = QReservation.reservation;
//        QPerformance performance = QPerformance.performance;
//        QPayment payment = QPayment.payment;
//        QHall hall = QHall.hall;
//
//        return queryFactory
//                .selectFrom(reservation)
//                .from(reservation)
//                .leftJoin(performance).on(reservation.performanceId.eq(performance.id))
//                .leftJoin(payment).on(reservation.paymentId.eq(payment.id))
//                .where(reservation.id.eq(reservationId))
//                .fetchOne();
//    }
//}