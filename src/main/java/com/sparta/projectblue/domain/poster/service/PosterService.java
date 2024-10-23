package com.sparta.projectblue.domain.poster.service;

import com.sparta.projectblue.domain.common.dto.AuthUser;
import com.sparta.projectblue.domain.performance.service.PerformanceService;
import com.sparta.projectblue.domain.poster.dto.PosterUpdateRequestDto;
import com.sparta.projectblue.domain.poster.entity.Poster;
import com.sparta.projectblue.domain.poster.repository.PosterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PosterService {

    private final PosterRepository posterRepository;
    private final PerformanceService performanceService;

    @Transactional
    public String update(AuthUser authUser, Long posterId, PosterUpdateRequestDto requestDto) {
        // Admin권한 확인, security에서 검증하면 없어져도됨.
        performanceService.hasRole(authUser);

        Poster poster = getPoster(posterId);

        poster.update(requestDto.getName(), requestDto.getImageUrl());

        return poster.getName() + "의 정보가 수정되었습니다.";
    }

    private Poster getPoster(Long posterId) {
        return posterRepository.findById(posterId).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 포스터입니다."));
    }
}
