package com.sparta.projectblue.domain.review.dto;

import com.sparta.projectblue.domain.common.enums.ReviewRate;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateReviewRequestDto {
    @NotNull
    private ReviewRate reviewRate;

    @NotNull
    private String contents;
}
