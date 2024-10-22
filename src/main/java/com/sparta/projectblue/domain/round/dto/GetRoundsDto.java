package com.sparta.projectblue.domain.round.dto;

import com.sparta.projectblue.domain.common.enums.PerformanceStatus;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class GetRoundsDto {

    @Getter
    public static class Response {
        private final List<RoundInfo> rounds;

        public Response(List<RoundInfo> rounds) {
            this.rounds = rounds;
        }
    }

    @Getter
    public static class RoundInfo {
        private final LocalDateTime date;
        private final PerformanceStatus status;

        public RoundInfo(LocalDateTime date, PerformanceStatus status) {
            this.date = date;
            this.status = status;
        }
    }
}
