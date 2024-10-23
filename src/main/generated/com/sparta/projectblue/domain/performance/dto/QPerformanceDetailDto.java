package com.sparta.projectblue.domain.performance.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.sparta.projectblue.domain.performance.dto.QPerformanceDetailDto is a Querydsl Projection type for PerformanceDetailDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QPerformanceDetailDto extends ConstructorExpression<PerformanceDetailDto> {

    private static final long serialVersionUID = -11154924L;

    public QPerformanceDetailDto(com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<java.time.LocalDateTime> startDate, com.querydsl.core.types.Expression<java.time.LocalDateTime> endDate, com.querydsl.core.types.Expression<Integer> price, com.querydsl.core.types.Expression<String> category, com.querydsl.core.types.Expression<String> description, com.querydsl.core.types.Expression<Integer> duration, com.querydsl.core.types.Expression<String> hallName) {
        super(PerformanceDetailDto.class, new Class<?>[]{String.class, java.time.LocalDateTime.class, java.time.LocalDateTime.class, int.class, String.class, String.class, int.class, String.class}, title, startDate, endDate, price, category, description, duration, hallName);
    }

}

