package com.sparta.projectblue.domain.poster.dto;

import lombok.Getter;

@Getter
public class PosterUpdateRequestDto {
    private String name;
    private String imageUrl;
    private Long fileSize;
}
