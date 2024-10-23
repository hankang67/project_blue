package com.sparta.projectblue.domain.search.service;

import com.sparta.projectblue.domain.performance.dto.PerformanceResponseDto;
import com.sparta.projectblue.domain.performance.repository.PerformanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SearchService {

    private final PerformanceRepository performanceRepository;

    /**
     * 공연 리스트 출력
     *
     * @param page
     * @param size          : 한 페이지에 나타날 공연 갯수
     * @param performanceNm : 검색할 공연의 이름
     * @param userSelectDay : 검색할 공연의 날짜
     * @return : 공연 정보(공연 이름, 공연장, 시작 날짜, 끝 날짜)
     */
    public Page<PerformanceResponseDto> searchPerformances(int page, int size, String performanceNm, String userSelectDay, String performer) {
        Pageable pageable = PageRequest.of(page - 1, size);

        LocalDateTime performanceDay
                = (userSelectDay != null) ? LocalDate.parse(userSelectDay).atTime(LocalTime.NOON) : LocalDateTime.now();

        return performanceRepository.findByCondition(pageable, performanceNm, performanceDay, performer);
    }
}
