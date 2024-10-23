package com.sparta.projectblue.domain.round.service;

import com.sparta.projectblue.domain.common.enums.PerformanceStatus;
import com.sparta.projectblue.domain.hall.entity.Hall;
import com.sparta.projectblue.domain.hall.repository.HallRepository;
import com.sparta.projectblue.domain.performance.entity.Performance;
import com.sparta.projectblue.domain.performance.repository.PerformanceRepository;
import com.sparta.projectblue.domain.round.dto.GetAvailableSeatsDto;
import com.sparta.projectblue.domain.round.entity.Round;
import com.sparta.projectblue.domain.round.repository.RoundRepository;
import com.sparta.projectblue.domain.seat.entity.ReservedSeat;
import com.sparta.projectblue.domain.seat.repository.ReservedSeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoundService {

    private final RoundRepository roundRepository;

    private final PerformanceRepository performanceRepository;
    private final HallRepository hallRepository;
    private final ReservedSeatRepository reservedSeatRepository;

    public GetAvailableSeatsDto.Response getAvailableSeats(Long id) {
        // 회차 가져옴
        Round round = roundRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("round not found"));

        // 오픈전
        if (round.getStatus().equals(PerformanceStatus.BEFORE_OPEN)) {
            throw new IllegalArgumentException("Reservation not yet open.");
        }

        // 매진
        if (round.getStatus().equals(PerformanceStatus.SOLD_OUT)) {
            throw new IllegalArgumentException("Sold out");
        }

        // 공연 가져옴
        Performance performance = performanceRepository.findById(round.getPerformanceId()).orElseThrow(() ->
                new IllegalArgumentException("performance not found"));

        // 공연장 가져옴
        Hall hall = hallRepository.findById(performance.getHallId()).orElseThrow(()->
                new IllegalArgumentException("hallo not found"));


        // 예약된 좌석 가져옴
        List<ReservedSeat> reservedSeats = reservedSeatRepository.findByRoundId(round.getId());
        Set<Integer> reservedSeatNumbers = reservedSeats.stream()
                .map(ReservedSeat::getSeatNumber)
                .collect(Collectors.toSet());

        // 전체 좌석수 가져옴
        int totalSeats = hall.getSeats();
        List<Integer> availableSeats = new ArrayList<>();

        // 예약된 자석 제외하고 리스트에 넣음
        for (int seatNumber = 1; seatNumber <= totalSeats; seatNumber++) {
            if (!reservedSeatNumbers.contains(seatNumber)) {
                availableSeats.add(seatNumber);
            }
        }

        return new GetAvailableSeatsDto.Response(performance.getTitle(), round.getDate(), availableSeats);
    }
}
