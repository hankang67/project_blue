package com.sparta.projectblue.domain.poster.dto;

import com.sparta.projectblue.domain.poster.entity.Poster;

import lombok.Getter;

@Getter
public class UpdatePosterResponseDto {

    private final Long performanceId;
    private final String name;
    private final String imageUrl;
    private final Long fileSize;

    public UpdatePosterResponseDto(Poster poster) {

        this.performanceId = poster.getPerformanceId();
        this.name = poster.getName();
        this.imageUrl = poster.getImageUrl();
        this.fileSize = poster.getFileSize();
    }
}
