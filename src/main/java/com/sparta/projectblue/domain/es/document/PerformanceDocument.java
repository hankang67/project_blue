package com.sparta.projectblue.domain.es.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.sparta.projectblue.domain.common.enums.Category;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Document(indexName = "performance_v3")
public class PerformanceDocument {
    @Id private Long id;

    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Text)
    private String description;

    @Field(type = FieldType.Keyword)
    private Category category; // 변경된 타입 확인

    @Field(type = FieldType.Integer)
    private int duration;

    @Field(type = FieldType.Long)
    private Long price;

    @Field(type = FieldType.Long)
    private Long startDate;

    @Field(type = FieldType.Long)
    private Long endDate;

    private Long hallId;

    public PerformanceDocument(
            Long id,
            String title,
            String description,
            Category category,
            int duration,
            Long price,
            Long startDate,
            Long endDate,
            Long hallId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.duration = duration;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.hallId = hallId;
    }
}
