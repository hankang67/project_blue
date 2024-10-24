package com.sparta.projectblue.domain.performer.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.projectblue.domain.performer.dto.PerformerDetailDto;
import com.sparta.projectblue.domain.performer.entity.QPerformer;
import com.sparta.projectblue.domain.performerPerformance.entity.QPerformerPerformance;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PerformerQueryRepositoryImpl implements PerformerQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<PerformerDetailDto> findPerformersByPerformanceId(Long performanceId) {
        QPerformer qPerformer = QPerformer.performer;
        QPerformerPerformance qPerformerPerformance = QPerformerPerformance.performerPerformance;

        List<PerformerDetailDto> performers = queryFactory
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

        return performers;
    }
}
