package com.sparta.projectblue.domain.performer.service;

import com.sparta.projectblue.domain.performance.repository.PerformanceRepository;
import com.sparta.projectblue.domain.performer.dto.PerformerDetailDto;
import com.sparta.projectblue.domain.performer.entity.Performer;
import com.sparta.projectblue.domain.performer.repository.PerformerRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PerformerService {

    private final PerformerRepository performerRepository;
    private final PerformanceRepository performanceRepository;

    //출연자 생성
    @Transactional
    public PerformerDetailDto.Response createPerformer(@Valid PerformerDetailDto requestDto) {
        try {
            LocalDate birthDate = LocalDate.parse(requestDto.getBirth());
            if (birthDate.isBefore(LocalDate.of(1900, 1, 1)) || birthDate.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("생일은 현재 날짜보다 이전이거나 1900년도 이후의 날짜여야 합니다.");
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("생일은 yyyy-MM-dd 형식이어야 합니다.");
        }


        Performer performer = new Performer(requestDto.getName(), requestDto.getBirth(), requestDto.getNation());
        Performer savedPerformer = performerRepository.save(performer);
        return new PerformerDetailDto.Response(savedPerformer.getName(), savedPerformer.getBirth(), savedPerformer.getNation());
    }
}
