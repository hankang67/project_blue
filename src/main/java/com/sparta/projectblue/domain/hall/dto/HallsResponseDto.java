package com.sparta.projectblue.domain.hall.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class HallsResponseDto {

    private final String name;
    private final Integer seats;
}
