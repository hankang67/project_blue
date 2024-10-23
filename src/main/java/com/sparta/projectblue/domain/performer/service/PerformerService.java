package com.sparta.projectblue.domain.performer.service;

import com.sparta.projectblue.domain.performance.repository.PerformanceRepository;
import com.sparta.projectblue.domain.performer.dto.PerformerDetailDto;
import com.sparta.projectblue.domain.performer.entity.Performer;
import com.sparta.projectblue.domain.performer.repository.PerformerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PerformerService {

    private final PerformerRepository performerRepository;
    private final PerformanceRepository performanceRepository;

    //출연자 생성
    @Transactional
    public PerformerDetailDto.Response createPerformer(PerformerDetailDto requestDto) {
        Performer performer = new Performer(requestDto.getName(), requestDto.getBirth(), requestDto.getNation());
        Performer savedPerformer = performerRepository.save(performer);
        return new PerformerDetailDto.Response(savedPerformer.getName(), savedPerformer.getBirth(), savedPerformer.getNation());
    }
}
