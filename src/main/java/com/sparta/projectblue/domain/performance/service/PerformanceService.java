package com.sparta.projectblue.domain.performance.service;

import com.sparta.projectblue.domain.performance.dto.PerformanceDetailDto;
import com.sparta.projectblue.domain.performance.dto.PerformanceResponseDto;
import com.sparta.projectblue.domain.performance.dto.PerformanceReviewDto;
import com.sparta.projectblue.domain.performance.dto.PerformanceRoundsDto;
import com.sparta.projectblue.domain.performance.entity.Performance;
import com.sparta.projectblue.domain.performance.repository.PerformanceRepository;
import com.sparta.projectblue.domain.performer.dto.PerformerDetailDto;
import com.sparta.projectblue.domain.performer.entity.Performer;
import com.sparta.projectblue.domain.performer.repository.PerformerRepository;
import com.sparta.projectblue.domain.performerPerformance.entity.PerformerPerformance;
import com.sparta.projectblue.domain.performerPerformance.repository.PerformerPerformanceRepository;
import com.sparta.projectblue.domain.review.entity.Review;
import com.sparta.projectblue.domain.review.repository.ReviewRepository;
import com.sparta.projectblue.domain.round.entity.Round;
import com.sparta.projectblue.domain.round.repository.RoundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PerformanceService {

    private final PerformanceRepository performanceRepository;

    private final PerformerRepository performerRepository;
    private final PerformerPerformanceRepository performerPerformanceRepository;
    private final ReviewRepository reviewRepository;
    private final RoundRepository roundRepository;

    // 진행중인 전체 공연 리스트 출력
    public Page<PerformanceResponseDto> getPerformances(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);

        LocalDateTime performanceDay = LocalDateTime.now();

        return performanceRepository.findByCondition(pageable, null, performanceDay, null);
    }

    public List<PerformerDetailDto> getPerformers(Long id) {
        return performerRepository.findPerformersByPerformanceId(id);
    }

    //공연 상세 정보 조회
    public PerformanceDetailDto getPerformance(Long id) {
        return performanceRepository.findPerformanceDetailById(id);
    }

    public GetRoundsDto.Response getRounds(Long id) {

        if (performanceRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("해당 공연을 찾을 수 없습니다.");
        }
        if (performerRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("해당 공연에 출연한 배우가 없습니다.");
        }

        // 회차 전체 조회
        List<Round> rounds = roundRepository.findByPerformanceId(id).stream().toList();
        // 회차 날짜정보, 예매 상태만 분리
        List<GetRoundsDto.RoundInfo> roundInfos = rounds.stream()
                .map(round -> new GetRoundsDto.RoundInfo(round.getDate(), round.getStatus()))
                .collect(Collectors.toList());

        return new GetRoundsDto.Response(roundInfos);

    }

    public PerformanceRoundsDto.Response getRounds(Long id) {
        // 공연 id값 검증
        if (performanceRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("공연을 찾을 수 없습니다");
        }

        // 회차 전체 조회
        List<Round> rounds = roundRepository.findByPerformanceId(id).stream().toList();

        // 회차 날짜정보, 예매 상태만 분리
        List<PerformanceRoundsDto.RoundInfo> roundInfos = rounds.stream()
                .map(round -> new PerformanceRoundsDto.RoundInfo(round.getDate(), round.getStatus()))
                .collect(Collectors.toList());

        return new PerformanceRoundsDto.Response(roundInfos);

    }

    public PerformanceReviewDto.Response getReviews(Long id) {
        // 공연 id값 검증
        if (performanceRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("공연을 찾을 수 없습니다");
        }

        List<Review> reviews = reviewRepository.findByPerformanceId(id).stream().toList();

        List<PerformanceReviewDto.ReviewInfo> reviewInfos = reviews.stream()
                .map(review -> new PerformanceReviewDto.ReviewInfo(review.getReviewRate(), review.getContent()))
                .collect(Collectors.toList());

        return new PerformanceReviewDto.Response(reviewInfos);
    }

    @Transactional
    public void addPerformer (Long performanceId, Long performerId){
        Performance performance = performanceRepository.findById(performanceId)
                .orElseThrow(() -> new IllegalArgumentException("해당 공연을 찾을 수 없습니다."));
        Performer performer = performerRepository.findById(performerId)
                .orElseThrow(() -> new IllegalArgumentException("해당 배우를 찾을 수 없습니다."));


        if (performerPerformanceRepository.existsByPerformanceIdAndPerformerId(performanceId, performerId)) {
            throw new IllegalArgumentException("해당 배우는 이미 이 공연에 등록되어 있습니다.");
        }

        PerformerPerformance performerPerformance = new PerformerPerformance(performerId, performanceId);
        performerPerformanceRepository.save(performerPerformance);

    }

    @Transactional
    public void removePerformer(Long performanceId, Long performerId) {
        Performance performance = performanceRepository.findById(performanceId)
                .orElseThrow(() -> new IllegalArgumentException("해당 공연을 찾을 수 없습니다."));
        Performer performer = performerRepository.findById(performerId)
                .orElseThrow(() -> new IllegalArgumentException("해당 배우를 찾을 수 없습니다."));

        PerformerPerformance performerPerformance = performerPerformanceRepository
                .findByPerformanceIdAndPerformerId(performanceId, performerId)
                .orElseThrow(() -> new IllegalArgumentException("해당 배우는 이 공연에 등록되어 있지 않습니다."));

        performerPerformanceRepository.delete(performerPerformance);
    }
}
