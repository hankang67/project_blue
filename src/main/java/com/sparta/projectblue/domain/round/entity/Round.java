package com.sparta.projectblue.domain.round.entity;

import com.sparta.projectblue.domain.common.entity.BaseEntity;
import com.sparta.projectblue.domain.common.enums.PerformanceStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "rounds")
public class Round extends BaseEntity {

    @Column(nullable = false)
    private Long performanceId;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private PerformanceStatus status;

    public Round(Long performanceId, LocalDateTime date, PerformanceStatus status) {
        this.performanceId = performanceId;
        this.date = date;
        this.status = status;
    }
}