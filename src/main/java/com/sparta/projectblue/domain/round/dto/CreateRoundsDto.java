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
        private List<LocalDateTime> dates;

        public Request(Long performanceId, List<LocalDateTime> dates) {
            this.performanceId = performanceId;
            this.dates = dates;
        }
    }
        @Getter
        public static class Response {
            private Long id;
            private Long performanceId;
            private LocalDateTime date;
            private PerformanceStatus status;

            public Response(Long id, Long performanceId, LocalDateTime date, PerformanceStatus status) {
                this.id = id;
                this.performanceId = performanceId;
                this.date = date;
                this.status = status;
            }
        }

        @Getter
        @NoArgsConstructor
        public static class UpdateRequest {
            private LocalDateTime date;
            private PerformanceStatus status;

            public UpdateRequest(LocalDateTime date, PerformanceStatus status) {
                this.date = date;
                this.status = status;
            }
        }


}