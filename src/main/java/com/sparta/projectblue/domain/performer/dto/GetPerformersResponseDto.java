package com.sparta.projectblue.domain.performer.dto;

import java.util.List;

import com.sparta.projectblue.domain.performer.entity.Performer;

import lombok.Getter;

@Getter
public class GetPerformersResponseDto {

    private final List<PerformerInfo> performers;

    public GetPerformersResponseDto(List<PerformerInfo> performers) {

        this.performers = performers;
    }

    @Getter
    public static class PerformerInfo {

        private final String name;
        private final String birth;

        public PerformerInfo(Performer performer) {

            this.name = performer.getName();
            this.birth = performer.getBirth();
        }
    }
}
