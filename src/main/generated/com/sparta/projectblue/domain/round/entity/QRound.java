package com.sparta.projectblue.domain.round.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRound is a Querydsl query type for Round
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRound extends EntityPathBase<Round> {

    private static final long serialVersionUID = -1988739060L;

    public static final QRound round = new QRound("round");

    public final com.sparta.projectblue.domain.common.entity.QBaseEntity _super = new com.sparta.projectblue.domain.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final DateTimePath<java.time.LocalDateTime> date = createDateTime("date", java.time.LocalDateTime.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final NumberPath<Long> performanceId = createNumber("performanceId", Long.class);

    public final EnumPath<com.sparta.projectblue.domain.common.enums.PerformanceStatus> status = createEnum("status", com.sparta.projectblue.domain.common.enums.PerformanceStatus.class);

    public QRound(String variable) {
        super(Round.class, forVariable(variable));
    }

    public QRound(Path<? extends Round> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRound(PathMetadata metadata) {
        super(Round.class, metadata);
    }

}

