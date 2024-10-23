package com.sparta.projectblue.domain.performance.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.projectblue.domain.hall.entity.QHall;
import com.sparta.projectblue.domain.performance.dto.PerformanceDetailDto;
import com.sparta.projectblue.domain.performance.dto.PerformanceResponseDto;
import com.sparta.projectblue.domain.performance.entity.QPerformance;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.sparta.projectblue.domain.hall.entity.QHall.hall;
import static com.sparta.projectblue.domain.performance.entity.QPerformance.performance;
import static com.sparta.projectblue.domain.performer.entity.QPerformer.performer;
import static com.sparta.projectblue.domain.performerPerformance.entity.QPerformerPerformance.performerPerformance;

@Repository
@RequiredArgsConstructor
public class PerformanceQueryRepositoryImpl implements PerformanceQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<PerformanceResponseDto> findByCondition(Pageable pageable, String performanceNm, LocalDateTime performanceDay, String performerNm) {

        List<PerformanceResponseDto> query = jpaQueryFactory
                .select(
                        Projections.fields(PerformanceResponseDto.class,
                                performance.title.as("title"),
                                hall.name.as("hallNm"),
                                performance.startDate.as("startDate"),
                                performance.endDate.as("endDate")
                        )
                )
                .from(performance)
                .leftJoin(hall).on(performance.hallId.eq(hall.id))
                .leftJoin(performerPerformance).on(performance.id.eq(performerPerformance.performanceId))
                .leftJoin(performer).on(performerPerformance.performerId.eq(performer.id))
                .where(
                        containPerformanceNm(performanceNm),
                        performanceBetweenIn(performanceDay),
                        containPerformerNm(performerNm)
                )
                .orderBy(performance.startDate.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = jpaQueryFactory
                .select(performance.count())
                .from(performance)
                .leftJoin(hall).on(performance.hallId.eq(hall.id))
                .leftJoin(performerPerformance).on(performance.id.eq(performerPerformance.performanceId))
                .leftJoin(performer).on(performerPerformance.performerId.eq(performer.id))
                .where(
                        containPerformanceNm(performanceNm),
                        performanceBetweenIn(performanceDay),
                        containPerformerNm(performerNm)
                )
                .fetchOne();

        if (total == null) total = 0L;

        return new PageImpl<>(query, pageable, total);
    }

    private BooleanExpression containPerformanceNm(String performanceNm) {
        return (performanceNm != null && !performanceNm.isEmpty())
                ? performance.title.containsIgnoreCase(performanceNm) : null;
    }

    private BooleanExpression containPerformerNm(String performerNm) {
        return (performerNm != null && !performerNm.isEmpty())
                ? performer.name.containsIgnoreCase(performerNm) : null;
    }

    private BooleanExpression performanceBetweenIn(LocalDateTime performanceDay) {
        BooleanExpression afterStart = performance.startDate.loe(performanceDay);
        BooleanExpression beforeEnd = performance.endDate.goe(performanceDay);

        return afterStart.and(beforeEnd);
    }

    @Override
    public PerformanceDetailDto findPerformanceDetailById(Long id) {
        QPerformance qPerformance = QPerformance.performance;
        QHall qHall = QHall.hall;

        PerformanceDetailDto performanceDetailDto = jpaQueryFactory
                .select(Projections.constructor(
                        PerformanceDetailDto.class,
                        qPerformance.title,
                        qPerformance.startDate,
                        qPerformance.endDate,
                        qPerformance.price,
                        qPerformance.category.stringValue(),
                        qPerformance.description,
                        qPerformance.duration,
                        qHall.name )
                )
                .from(qPerformance)
                .leftJoin(qHall).on(qPerformance.hallId.eq(qHall.id))
                .where(qPerformance.id.eq(id))
                .fetchOne();

        if (performanceDetailDto == null) {
            throw new IllegalArgumentException("해당 공연 정보를 찾을 수 없습니다.");
        }

        return performanceDetailDto;
    }
}
