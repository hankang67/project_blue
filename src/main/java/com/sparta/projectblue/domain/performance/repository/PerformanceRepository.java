package com.sparta.projectblue.domain.performance.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.projectblue.domain.hall.entity.QHall;
import com.sparta.projectblue.domain.performance.dto.PerformanceDetailDto;
import com.sparta.projectblue.domain.performance.entity.Performance;
import com.sparta.projectblue.domain.performance.entity.QPerformance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceRepository extends JpaRepository<Performance, Long> {

    // 특정 공연의 세부 정보를 조회
    default PerformanceDetailDto findPerformanceDetailById(Long id, JPAQueryFactory queryFactory) {
        QPerformance qPerformance = QPerformance.performance;
        QHall qHall = QHall.hall;

        return queryFactory
                .select(Projections.constructor(
                        PerformanceDetailDto.class,
                        qPerformance.title,
                        qPerformance.startDate,
                        qPerformance.endDate,
                        qPerformance.price,
                        qPerformance.category.stringValue(),
                        qPerformance.description,
                        qPerformance.duration,
                        qHall.name))
                        .from(qPerformance)
                        .leftJoin(qHall).on(qPerformance.hallId.eq(qHall.id))
                        .where(qPerformance.id.eq(id))
                        .fetchOne();
    }
}

