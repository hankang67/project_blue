package com.sparta.projectblue.domain.poster.entity;

import com.sparta.projectblue.domain.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "posters")
public class Poster extends BaseEntity {

    @Column(nullable = false)
    private Long performanceId;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private String imageUrl;

    public Poster(Long performanceId, String name, String imageUrl) {
        this.performanceId = performanceId;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public void update(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }
}
