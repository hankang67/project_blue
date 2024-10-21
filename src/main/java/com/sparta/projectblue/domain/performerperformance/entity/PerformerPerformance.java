package com.sparta.projectblue.domain.performerperformance.entity;

import com.sparta.projectblue.domain.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "performer_performance")
public class PerformerPerformance extends BaseEntity {

    @Column(nullable = false)
    private Long performerId;

    @Column(nullable = false)
    private Long performanceId;

    public PerformerPerformance(Long performerId, Long performanceId) {
        this.performerId = performerId;
        this.performanceId = performanceId;
    }
}
