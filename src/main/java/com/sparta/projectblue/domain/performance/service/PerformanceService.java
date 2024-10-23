package com.sparta.projectblue.domain.performance.service;

import com.sparta.projectblue.domain.performance.repository.PerformanceRepository;
import com.sparta.projectblue.domain.round.dto.GetRoundsDto;
import com.sparta.projectblue.domain.round.entity.Round;
import com.sparta.projectblue.domain.round.repository.RoundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PerformanceService {

    private final PerformanceRepository performanceRepository;

    private final RoundRepository roundRepository;

    public GetRoundsDto.Response getRounds(Long id) {

        // 공연 id값 검증
        if (performanceRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("performance not found");
        }

        // 회차 전체 조회
        List<Round> rounds = roundRepository.findByPerformanceId(id).stream().toList();

        // 회차 날짜정보, 예매 상태만 분리
        List<GetRoundsDto.RoundInfo> roundInfos = rounds.stream()
                .map(round -> new GetRoundsDto.RoundInfo(round.getDate(), round.getStatus()))
                .collect(Collectors.toList());

        return new GetRoundsDto.Response(roundInfos);
    }
}
