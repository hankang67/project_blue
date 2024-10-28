package com.sparta.projectblue.domain.hall.dto;

import com.sparta.projectblue.domain.hall.entity.Hall;

import lombok.Getter;

@Getter
public class GetHallResponseDto {

    private final Long id;
    private final String name;
    private final String address;
    private final Integer seats;

    public GetHallResponseDto(Hall hall) {

        this.id = hall.getId();
        this.name = hall.getName();
        this.address = hall.getAddress();
        this.seats = hall.getSeats();
    }
}
