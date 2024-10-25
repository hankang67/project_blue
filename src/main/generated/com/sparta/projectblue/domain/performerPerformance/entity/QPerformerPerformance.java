package com.sparta.projectblue.domain.performerPerformance.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPerformerPerformance is a Querydsl query type for PerformerPerformance
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPerformerPerformance extends EntityPathBase<PerformerPerformance> {

    private static final long serialVersionUID = -1210598742L;

    public static final QPerformerPerformance performerPerformance = new QPerformerPerformance("performerPerformance");

    public final com.sparta.projectblue.domain.common.entity.QBaseEntity _super = new com.sparta.projectblue.domain.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final NumberPath<Long> performanceId = createNumber("performanceId", Long.class);

    public final NumberPath<Long> performerId = createNumber("performerId", Long.class);

    public QPerformerPerformance(String variable) {
        super(PerformerPerformance.class, forVariable(variable));
    }

    public QPerformerPerformance(Path<? extends PerformerPerformance> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPerformerPerformance(PathMetadata metadata) {
        super(PerformerPerformance.class, metadata);
    }

}

