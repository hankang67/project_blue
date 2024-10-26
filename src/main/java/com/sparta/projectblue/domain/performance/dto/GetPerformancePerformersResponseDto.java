package com.sparta.projectblue.domain.performance.dto;

import java.util.List;

import com.sparta.projectblue.domain.performer.entity.Performer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class GetPerformancePerformersResponseDto {

    private final List<PerformerInfo> performers;

    public GetPerformancePerformersResponseDto(List<PerformerInfo> performers) {
        this.performers = performers;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PerformerInfo {

        private String name;
        private String nation;
        private String birth;

        public PerformerInfo(Performer performer) {

            this.name = performer.getName();
            this.nation = performer.getNation();
            this.birth = performer.getBirth();
        }
    }
}
