package com.sparta.projectblue.domain.poster.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPoster is a Querydsl query type for Poster
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPoster extends EntityPathBase<Poster> {

    private static final long serialVersionUID = 60692672L;

    public static final QPoster poster = new QPoster("poster");

    public final com.sparta.projectblue.domain.common.entity.QBaseEntity _super = new com.sparta.projectblue.domain.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath imageUrl = createString("imageUrl");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath name = createString("name");

    public final NumberPath<Long> performanceId = createNumber("performanceId", Long.class);

    public QPoster(String variable) {
        super(Poster.class, forVariable(variable));
    }

    public QPoster(Path<? extends Poster> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPoster(PathMetadata metadata) {
        super(Poster.class, metadata);
    }

}

