package com.sparta.projectblue.domain.performer.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.sparta.projectblue.config.CacheKey;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sparta.projectblue.domain.performer.dto.GetPerformerResponseDto;
import com.sparta.projectblue.domain.performer.dto.GetPerformersResponseDto;
import com.sparta.projectblue.domain.performer.entity.Performer;
import com.sparta.projectblue.domain.performer.repository.PerformerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PerformerService {

    private final PerformerRepository performerRepository;

    @Cacheable(value=CacheKey.PERFORMER, key = "#id")
    public GetPerformerResponseDto getPerformer(Long id) {

        Performer performer =
                performerRepository
                        .findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("배우를 찾을 수 없습니다."));

        return new GetPerformerResponseDto(performer);
    }

    @Cacheable(value = CacheKey.PERFORMERS, key = "'all_performers'")
    public GetPerformersResponseDto getPerformers() {

        List<GetPerformersResponseDto.PerformerInfo> performers =
                performerRepository.findAll().stream()
                        .map(GetPerformersResponseDto.PerformerInfo::new)
                        .collect(Collectors.toList());

        return new GetPerformersResponseDto(performers);
    }
}
