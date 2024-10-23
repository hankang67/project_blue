package com.sparta.projectblue.domain.performer.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.projectblue.domain.performer.dto.PerformerDetailDto;
import com.sparta.projectblue.domain.performer.entity.Performer;
import com.sparta.projectblue.domain.performer.entity.QPerformer;
import com.sparta.projectblue.domain.performerperformance.entity.QPerformerPerformance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.Collectors;

public interface PerformerRepository extends JpaRepository<Performer, Long> {
    // 특정 공연의 출연자 목록 조회
    default List<PerformerDetailDto> findPerformersByPerformanceId(Long performanceId, JPAQueryFactory queryFactory) {
        QPerformer qPerformer = QPerformer.performer;
        QPerformerPerformance qPerformerPerformance = QPerformerPerformance.performerPerformance;

        List<PerformerDetailDto> performers =  queryFactory
                .select(qPerformer.name, qPerformer.birth, qPerformer.nation)
                .from(qPerformerPerformance)
                .join(qPerformer).on(qPerformerPerformance.performerId.eq(qPerformer.id))
                .where(qPerformerPerformance.performanceId.eq(performanceId))
                .fetch()
                .stream()
                .map(tuple -> new PerformerDetailDto(
                        tuple.get(qPerformer.name),
                        tuple.get(qPerformer.birth),
                        tuple.get(qPerformer.nation)
                ))
                .collect(Collectors.toList());
        if (performers.isEmpty()) {
            throw new IllegalArgumentException("해당 공연에 대한 출연자 정보를 찾을 수 없습니다.");
        }

        return performers;
    }
}
