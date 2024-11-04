package com.sparta.projectblue.domain.round.service;

import com.sparta.projectblue.domain.common.enums.PerformanceStatus;
import com.sparta.projectblue.domain.hall.repository.HallRepository;
import com.sparta.projectblue.domain.performance.repository.PerformanceRepository;
import com.sparta.projectblue.domain.reservedSeat.repository.ReservedSeatRepository;
import com.sparta.projectblue.domain.round.dto.CreateRoundRequestDto;
import com.sparta.projectblue.domain.round.dto.CreateRoundResponseDto;
import com.sparta.projectblue.domain.round.dto.UpdateRoundRequestDto;
import com.sparta.projectblue.domain.round.dto.UpdateRoundResponseDto;
import com.sparta.projectblue.domain.round.entity.Round;
import com.sparta.projectblue.domain.round.repository.RoundRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class RoundAdminService {

    private final RoundRepository roundRepository;
    private final PerformanceRepository performanceRepository;

    @Transactional
    public List<CreateRoundResponseDto> create(Long id, CreateRoundRequestDto request) {

        // 공연 존재 여부 확인
        if (performanceRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("공연을 찾을 수 없습니다");
        }
        LocalDateTime now = LocalDateTime.now();

        List<Round> newRounds =
                request.getDates().stream()
                        .peek(
                                date -> {
                                    if (date.isBefore(now)) {
                                        throw new IllegalArgumentException(
                                                "과거의 날짜로 회차를 생성할 수 없습니다.");
                                    }
                                })
                        .map(date -> new Round(id, date, PerformanceStatus.BEFORE_OPEN))
                        .collect(Collectors.toList());

        List<Round> savedRounds = roundRepository.saveAll(newRounds);

        return savedRounds.stream().map(CreateRoundResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public UpdateRoundResponseDto update(Long id, UpdateRoundRequestDto request) {

        // 회차 가져옴
        Round round =
                roundRepository
                        .findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("회차를 찾을 수 없습니다."));

        LocalDateTime now = LocalDateTime.now();

        if (request.getDate() != null) {
            if (request.getDate().isBefore(now)) {
                throw new IllegalArgumentException("과거의 날짜로 회차를 수정할 수 없습니다.");
            }
            round.updateDate(request.getDate());
        } else {
            request = new UpdateRoundRequestDto(round.getDate(), request.getStatus());
        }

        PerformanceStatus newStatus = request.getStatus() != null ? request.getStatus() : round.getStatus();
        round.updateStatus(newStatus);

        roundRepository.save(round);

        return new UpdateRoundResponseDto(round);
    }

    @Transactional
    public void delete(Long id) {

        Round round =
                roundRepository
                        .findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("회차를 찾을 수 없습니다."));

        roundRepository.delete(round);
    }

}
