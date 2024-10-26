package com.sparta.projectblue.domain.performance.dto;

import lombok.Getter;

@Getter
public class UpdatePerformanceResponseDto {

    private final Long id;
    private final String title;

    public UpdatePerformanceResponseDto(Long id, String title) {

        this.id = id;
        this.title = title;
    }
}
