package com.sparta.projectblue.domain.performance.dto;

import lombok.Getter;

@Getter
public class CreatePerformanceResponseDto {
    private final Long id;
    private final String title;

    public CreatePerformanceResponseDto(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
