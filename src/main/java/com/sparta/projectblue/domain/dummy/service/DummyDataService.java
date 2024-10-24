package com.sparta.projectblue.domain.dummy.service;

import com.sparta.projectblue.domain.dummy.generator.DummyUserGenerator;
import com.sparta.projectblue.domain.hall.entity.Hall;
import com.sparta.projectblue.domain.hall.repository.HallRepository;
import com.sparta.projectblue.domain.performer.entity.Performer;
import com.sparta.projectblue.domain.performer.repository.PerformerRepository;
import com.sparta.projectblue.domain.poster.entity.Poster;
import com.sparta.projectblue.domain.poster.repository.PosterRepository;
import com.sparta.projectblue.domain.reservation.entity.Reservation;
import com.sparta.projectblue.domain.reservation.repository.ReservationRepository;
import com.sparta.projectblue.domain.review.entity.Review;
import com.sparta.projectblue.domain.review.repository.ReviewRepository;
import com.sparta.projectblue.domain.round.entity.Round;
import com.sparta.projectblue.domain.round.repository.RoundRepository;
import com.sparta.projectblue.domain.user.entity.User;
import com.sparta.projectblue.domain.user.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class DummyDataService {
    @Autowired
    private HallRepository hallRepository;

    @Autowired
    private PerformerRepository performerRepository;

    @Autowired
    private PosterRepository posterRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private RoundRepository roundRepository;

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init() {

        // 더미 사용자 생성 및 저장
        List<User> users = IntStream.range(0, 10000)
                .mapToObj(i -> DummyUserGenerator.generate())
                .collect(Collectors.toList());
        userRepository.saveAll(users);

//        // 더미 공연장 생성 및 저장
//        List<Hall> halls = IntStream.range(0, 100)
//                .mapToObj(i -> DummyHallGenerator.generate())
//                .collect(Collectors.toList());
//        hallRepository.saveAll(halls);
//
//        // 더미 공연자 생성 및 저장
//        List<Performer> performers = IntStream.range(0, 500)
//                .mapToObj(i -> DummyPerformerGenerator.generate())
//                .collect(Collectors.toList());
//        performerRepository.saveAll(performers);
//
//        // 더미 공연 회차 생성 및 저장
//        List<Round> rounds = IntStream.range(0, 2000)
//                .mapToObj(i -> DummyRoundGenerator.generate(halls, users))
//                .collect(Collectors.toList());
//        roundRepository.saveAll(rounds);
//
//        // 더미 포스터 생성 및 저장
//        List<Poster> posters = IntStream.range(0, 200)
//                .mapToObj(i -> DummyPosterGenerator.generate(rounds))
//                .collect(Collectors.toList());
//        posterRepository.saveAll(posters);
//
//        // 더미 예약 생성 및 저장
//        List<Reservation> reservations = IntStream.range(0, 5000)
//                .mapToObj(i -> DummyReservationGenerator.generate(users, rounds))
//                .collect(Collectors.toList());
//        reservationRepository.saveAll(reservations);
//
//        // 더미 리뷰 생성 및 저장
//        List<Review> reviews = IntStream.range(0, 3000)
//                .mapToObj(i -> DummyReviewGenerator.generate(reservations, rounds))
//                .collect(Collectors.toList());
//        reviewRepository.saveAll(reviews);
//    }
}
}
