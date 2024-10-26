package com.sparta.projectblue.domain.performer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePerformerRequestDto {

    @NotBlank
    private String name;

    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "생일은 yyyy-MM-dd 형식이어야 합니다.")
    private String birth;

    private String nation;
}
