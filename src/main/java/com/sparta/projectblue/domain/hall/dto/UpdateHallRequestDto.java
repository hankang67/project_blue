package com.sparta.projectblue.domain.hall.dto;

import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateHallRequestDto {

    @NotNull private String name;

    @NotNull private String address;

    @NotNull private Integer seats;
}
