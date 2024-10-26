package com.sparta.projectblue.domain.poster.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import com.sparta.projectblue.domain.common.entity.BaseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "posters")
public class Poster extends BaseEntity {

    @Column(nullable = false, name = "performance_id")
    private Long performanceId;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 255, name = "image_url")
    private String imageUrl;

    @Column(nullable = false, name = "file_size")
    private Long fileSize;

    public Poster(Long performanceId, String name, String imageUrl, Long fileSize) {

        this.performanceId = performanceId;
        this.name = name;
        this.imageUrl = imageUrl;
        this.fileSize = fileSize;
    }

    public void update(String name, String imageUrl, Long fileSize) {

        this.name = name;
        this.imageUrl = imageUrl;
        this.fileSize = fileSize;
    }
}
