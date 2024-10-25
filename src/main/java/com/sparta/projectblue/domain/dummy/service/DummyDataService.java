//package com.sparta.projectblue.domain.dummy.service;
//
//import com.sparta.projectblue.domain.dummy.generator.*;
//import com.sparta.projectblue.domain.hall.entity.Hall;
//import com.sparta.projectblue.domain.hall.repository.HallRepository;
//import com.sparta.projectblue.domain.performance.entity.Performance;
//import com.sparta.projectblue.domain.performance.repository.PerformanceRepository;
//import com.sparta.projectblue.domain.performer.entity.Performer;
//import com.sparta.projectblue.domain.performer.repository.PerformerRepository;
//import com.sparta.projectblue.domain.poster.entity.Poster;
//import com.sparta.projectblue.domain.poster.repository.PosterRepository;
//import com.sparta.projectblue.domain.reservation.entity.Reservation;
//import com.sparta.projectblue.domain.reservation.repository.ReservationRepository;
//import com.sparta.projectblue.domain.review.entity.Review;
//import com.sparta.projectblue.domain.review.repository.ReviewRepository;
//import com.sparta.projectblue.domain.round.entity.Round;
//import com.sparta.projectblue.domain.round.repository.RoundRepository;
//import com.sparta.projectblue.domain.user.entity.User;
//import com.sparta.projectblue.domain.user.repository.UserRepository;
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//
//@Component
//public class DummyDataService {
//    @Autowired
//    private HallRepository hallRepository;
//
//    @Autowired
//    private PerformanceRepository performanceRepository;
//
//    @Autowired
//    private PerformerRepository performerRepository;
//
//    @Autowired
//    private PosterRepository posterRepository;
//
//    @Autowired
//    private ReservationRepository reservationRepository;
//
//    @Autowired
//    private ReviewRepository reviewRepository;
//
//    @Autowired
//    private RoundRepository roundRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @PostConstruct
//    public void init() {
//
//        // 더미 유저 생성 및 저장
//        List<User> users = IntStream.range(0, 10000)
//                .mapToObj(i -> DummyUserGenerator.generate())
//                .collect(Collectors.toList());
//        userRepository.saveAll(users);
//
//        // 더미 배우 생성 및 저장
//        List<Performer> performers = IntStream.range(0, 500)
//                .mapToObj(i -> DummyPerformerGenerator.generate())
//                .collect(Collectors.toList());
//        performerRepository.saveAll(performers);
//
//        // 더미 홀 생성 및 저장
//        List<Hall> halls = IntStream.range(0, 100000)
//                .mapToObj(i -> DummyHallGenerator.generate())
//                .collect(Collectors.toList());
//        hallRepository.saveAll(halls);
//
//        // 공연장 ID 목록 생성
//        List<Long> hallIds = halls.stream()
//                .map(Hall::getId)
//                .collect(Collectors.toList());
//
//        // 더미 공연 생성 및 저장
//        List<Performance> performances = IntStream.range(0, 500)
//                .mapToObj(i -> DummyPerformanceGenerator.generate(hallIds))
//                .collect(Collectors.toList());
//        performanceRepository.saveAll(performances);
//
//        // 더미 공연 회차 생성 및 저장
//        List<Round> rounds = IntStream.range(0, 2000)
//                .mapToObj(i -> DummyRoundGenerator.generate(hallIds))
//                .collect(Collectors.toList());
//        roundRepository.saveAll(rounds);
//
//        // 공연 ID 목록 생성
//        List<Long> performanceIds = rounds.stream()
//                .map(Round::getPerformanceId)
//                .distinct()
//                .collect(Collectors.toList());
//
//        // 더미 포스터 생성 및 저장
//        List<Poster> posters = IntStream.range(0, 200)
//                .mapToObj(i -> DummyPosterGenerator.generate(performanceIds))
//                .collect(Collectors.toList());
//        posterRepository.saveAll(posters);
//
//
//
//    }
//}
//
