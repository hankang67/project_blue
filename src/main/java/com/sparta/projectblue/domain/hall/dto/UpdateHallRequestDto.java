package com.sparta.projectblue.domain.hall.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateHallRequestDto {

    private String name;

    private String address;

    private Integer seats;
}
