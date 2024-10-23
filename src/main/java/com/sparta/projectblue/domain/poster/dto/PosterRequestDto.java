package com.sparta.projectblue.domain.poster.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class PosterRequestDto {
    @NotNull private Long performanceId;
    @NotBlank private String name;
    @NotBlank private String imageUrl;
}
