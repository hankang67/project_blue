package com.sparta.projectblue.domain.round.entity;

import com.sparta.projectblue.domain.common.entity.BaseEntity;
import com.sparta.projectblue.domain.common.enums.PerformanceStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "rounds")
public class Round extends BaseEntity {

    @Column(nullable = false, name = "performance_id")
    private Long performanceId;

    @Column(nullable = false)
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PerformanceStatus status;

    public Round(Long performanceId, LocalDateTime date, PerformanceStatus status) {
        this.performanceId = performanceId;
        this.date = date;
        this.status = status;
    }
    public void updateDate(LocalDateTime date) {
        this.date = date;
    }

    public void updateStatus(PerformanceStatus status) {
        this.status = status;
    }
}