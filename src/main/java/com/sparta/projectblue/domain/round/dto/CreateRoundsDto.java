package com.sparta.projectblue.domain.round.dto;

import com.sparta.projectblue.domain.common.enums.PerformanceStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class CreateRoundsDto {
    @Getter
    @NoArgsConstructor
    public static class Request {
        private Long performanceId;
        private List<RoundInfo> rounds;

        @Getter
        @NoArgsConstructor
        public static class RoundInfo {
            private LocalDateTime date;
            private PerformanceStatus status;

            public RoundInfo(LocalDateTime date, PerformanceStatus status) {
                this.date = date;
                this.status = status;
            }
        }
    }

    @Getter
    public static class Response {
        private final String message;

        public Response(String message) {
            this.message = message;
        }
    }
}
