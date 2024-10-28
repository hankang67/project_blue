package com.sparta.projectblue.domain.reservation.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateReservationRequestDto {

    @NotNull private Long roundId;

    @NotNull private List<Integer> seats;
}
