package com.sparta.projectblue.domain.performer.dto;

import java.time.LocalDateTime;

import com.sparta.projectblue.domain.performer.entity.Performer;

import lombok.Getter;

@Getter
public class GetPerformerResponseDto {

    private final String name;
    private final String nation;
    private final String birth;
    private final LocalDateTime createdAt;

    public GetPerformerResponseDto(Performer performer) {

        this.name = performer.getName();
        this.nation = performer.getNation();
        this.birth = performer.getBirth();
        this.createdAt = performer.getCreatedAt();
    }
}
