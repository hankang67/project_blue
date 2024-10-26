package com.sparta.projectblue.domain.performer.service;

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

    @Transactional(readOnly = true)
    public PerformerDetailDto.Response getPerformer(Long id) {
        Performer performer = performerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("배우를 찾을 수 없습니다."));

        return new PerformerDetailDto.Response(
                performer.getName(),
                performer.getBirth(),
                performer.getNation()
        );
    }
}
