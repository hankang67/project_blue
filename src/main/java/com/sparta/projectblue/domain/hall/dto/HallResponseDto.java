package com.sparta.projectblue.domain.hall.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class HallResponseDto {

    private final Long id;
    private final String name;
    private final String address;
    private final Integer seats;
}
