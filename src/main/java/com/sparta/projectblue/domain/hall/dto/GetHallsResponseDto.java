package com.sparta.projectblue.domain.hall.dto;

import com.sparta.projectblue.domain.hall.entity.Hall;

import lombok.Getter;

@Getter
public class GetHallsResponseDto {

    private final String name;
    private final Integer seats;

    public GetHallsResponseDto(Hall hall) {

        this.name = hall.getName();
        this.seats = hall.getSeats();
    }
}
