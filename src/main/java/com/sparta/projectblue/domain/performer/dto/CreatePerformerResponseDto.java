package com.sparta.projectblue.domain.performer.dto;

import com.sparta.projectblue.domain.performer.entity.Performer;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreatePerformerResponseDto {
    private final Long id;
    private final String name;
    private final String birth;
    private final String nation;
    private final LocalDateTime createdAt;

    public CreatePerformerResponseDto(Performer performer) {
        this.id = performer.getId();
        this.name = performer.getName();
        this.birth = performer.getBirth();
        this.nation = performer.getNation();
        this.createdAt = performer.getCreatedAt();
    }
}
