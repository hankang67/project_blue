package com.sparta.projectblue.domain.hall.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HallRequestDto {

    @NotNull
    private String name;
    @NotNull
    private String address;
    @NotNull
    private int seats;
}
