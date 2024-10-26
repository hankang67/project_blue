package com.sparta.projectblue.domain.hall.dto;

import com.sparta.projectblue.domain.hall.entity.Hall;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
public class CreateHallResponseDto {

    private final Long id;
    private final String name;
    private final String address;
    private final Integer seats;
    private final LocalDateTime createdAt;

    public CreateHallResponseDto(Hall hall) {
        this.id = hall.getId();
        this.name = hall.getName();
        this.address = hall.getAddress();
        this.seats = hall.getSeats();
        this.createdAt = hall.getCreatedAt();
    }
}
