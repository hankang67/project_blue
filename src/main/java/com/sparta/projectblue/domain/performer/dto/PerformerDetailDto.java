package com.sparta.projectblue.domain.performer.dto;

import lombok.Getter;

@Getter
public class PerformerDetailDto {
    private String name;
    private String birth;
    private String nation;

    public PerformerDetailDto(String name, String birth, String nation) {
        this.name = name;
        this.birth = birth;
        this.nation = nation;
    }
}
