package com.sparta.projectblue.domain.performer.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPerformer is a Querydsl query type for Performer
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPerformer extends EntityPathBase<Performer> {

    private static final long serialVersionUID = 2129669260L;

    public static final QPerformer performer = new QPerformer("performer");

    public final com.sparta.projectblue.domain.common.entity.QBaseEntity _super = new com.sparta.projectblue.domain.common.entity.QBaseEntity(this);

    public final StringPath birth = createString("birth");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath name = createString("name");

    public final StringPath nation = createString("nation");

    public QPerformer(String variable) {
        super(Performer.class, forVariable(variable));
    }

    public QPerformer(Path<? extends Performer> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPerformer(PathMetadata metadata) {
        super(Performer.class, metadata);
    }

}

