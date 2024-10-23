package com.sparta.projectblue.domain.poster.service;

import com.sparta.projectblue.domain.common.dto.AuthUser;
import com.sparta.projectblue.domain.performance.service.PerformanceService;
import com.sparta.projectblue.domain.poster.dto.PosterRequestDto;
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

    public String create(AuthUser authUser, PosterRequestDto requestDto) {
        // Admin권한 확인, security에서 검증하면 없어져도됨.
        performanceService.hasRole(authUser);

        if(posterRepository.existsByPerformanceId(requestDto.getPerformanceId())) {
            throw new IllegalArgumentException("포스터 이미지는 하나만 등록 가능합니다.");
        }

        Poster poster = new Poster(
                requestDto.getPerformanceId(),
                requestDto.getName(),
                requestDto.getImageUrl());

        posterRepository.save(poster);

        return requestDto.getName() + " 포스터 등록 완료";
    }

    @Transactional
    public String update(AuthUser authUser, Long posterId, PosterUpdateRequestDto requestDto) {
        // Admin권한 확인, security에서 검증하면 없어져도됨.
        performanceService.hasRole(authUser);

        Poster poster = getPoster(posterId);

        poster.update(requestDto.getName(), requestDto.getImageUrl());

        return poster.getName() + "의 정보가 수정되었습니다.";
    }

    public String delete(AuthUser authUser, Long posterId) {
        // Admin권한 확인, security에서 검증하면 없어져도됨.
        performanceService.hasRole(authUser);

        Poster poster = getPoster(posterId);

        posterRepository.delete(poster);

        return poster.getName() + "이(가) 삭제되었습니다.";
    }

    private Poster getPoster(Long posterId) {
        return posterRepository.findById(posterId).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 포스터입니다."));
    }
}
