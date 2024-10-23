package com.sparta.projectblue.domain.performer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class PerformerDetailDto {

    @NotBlank(message = "이름은 필수 항목입니다.")
    private String name;

    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "생일은 yyyy-MM-dd 형식이어야 합니다.")
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

