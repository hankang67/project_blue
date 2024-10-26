package com.sparta.projectblue.domain.hall.dto;

import com.sparta.projectblue.domain.hall.entity.Hall;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateHallResponseDto {

    private final Long id;
    private final String name;
    private final String address;
    private final Integer seats;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public UpdateHallResponseDto(Hall hall) {
        this.id = hall.getId();
        this.name = hall.getName();
        this.address = hall.getAddress();
        this.seats = hall.getSeats();
        this.createdAt = hall.getCreatedAt();
        this.modifiedAt = hall.getModifiedAt();
    }
}
