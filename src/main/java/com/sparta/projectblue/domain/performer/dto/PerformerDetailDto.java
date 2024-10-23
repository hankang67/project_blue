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

    @Getter
    public static class Response {
        private final String name;
        private final String birth;
        private final String nation;

        public Response(String name, String birth, String nation) {
            this.name = name;
            this.birth = birth;
            this.nation = nation;
        }

        public static Response fromEntity(PerformerDetailDto dto) {
            return new Response(dto.getName(), dto.getBirth(), dto.getNation());
        }
    }

}

