package com.sparta.projectblue.domain.performance.service;

import com.sparta.projectblue.domain.performance.dto.PerformanceDetailDto;
import com.sparta.projectblue.domain.performance.dto.PerformanceResponseDto;
import com.sparta.projectblue.domain.performance.repository.PerformanceRepository;
import com.sparta.projectblue.domain.performer.dto.PerformerDetailDto;
import com.sparta.projectblue.domain.performer.repository.PerformerRepository;
import com.sparta.projectblue.domain.performer.service.PerformerService;
import com.sparta.projectblue.domain.round.dto.GetRoundsDto;
import com.sparta.projectblue.domain.round.entity.Round;
import com.sparta.projectblue.domain.round.repository.RoundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PerformanceService {

    private final PerformanceRepository performanceRepository;

    private final RoundRepository roundRepository;
    private final PerformerRepository performerRepository;

    // 진행중인 전체 공연 리스트 출력
    public Page<PerformanceResponseDto> getPerformances(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);

        LocalDateTime performanceDay = LocalDateTime.now();

        return performanceRepository.findByCondition(pageable, null, performanceDay, null);
    }

    /**
     * 공연 리스트 출력
     *
     * @param page
     * @param size          : 한 페이지에 나타날 공연 갯수
     * @param performanceNm : 검색할 공연의 이름
     * @param userSelectDay : 검색할 공연의 날짜
     * @return : 공연 정보(공연 이름, 공연장, 시작 날짜, 끝 날짜)
     */
    public Page<PerformanceResponseDto> getFilterPerformances(int page, int size, String performanceNm, String userSelectDay, String performer) {
        Pageable pageable = PageRequest.of(page - 1, size);

        LocalDateTime performanceDay
                = (userSelectDay != null) ? LocalDate.parse(userSelectDay).atTime(LocalTime.NOON) : LocalDateTime.now();

        return performanceRepository.findByCondition(pageable, performanceNm, performanceDay, performer);
    }


    //공연 상세 정보 조회
    public PerformanceDetailDto getPerformance(Long id) {
        return performanceRepository.findPerformanceDetailById(id);
    }

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

    public List<PerformerDetailDto> getPerformers(Long id) {
        return performerRepository.findPerformersByPerformanceId(id);
    }

}
