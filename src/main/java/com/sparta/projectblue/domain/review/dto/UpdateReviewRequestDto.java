package com.sparta.projectblue.domain.review.dto;

import jakarta.validation.constraints.NotNull;

import com.sparta.projectblue.domain.common.enums.ReviewRate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateReviewRequestDto {

    @NotNull private ReviewRate reviewRate;

    @NotNull private String contents;
}
