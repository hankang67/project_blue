package com.sparta.projectblue.domain.search.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sparta.projectblue.domain.performance.dto.GetPerformancesResponseDto;
import com.sparta.projectblue.domain.performance.repository.PerformanceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SearchService {

    private final PerformanceRepository performanceRepository;

    public Page<GetPerformancesResponseDto> searchPerformances(
            int page, int size, String performanceNm, String userSelectDay, String performer) {
        Pageable pageable = PageRequest.of(page - 1, size);

        LocalDateTime performanceDay =
                (userSelectDay != null)
                        ? LocalDate.parse(userSelectDay).atTime(LocalTime.NOON)
                        : LocalDateTime.now();

        return performanceRepository.findByCondition(
                pageable, performanceNm, performanceDay, performer);
    }
}
