package com.sparta.projectblue.domain.hall.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QHall is a Querydsl query type for Hall
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHall extends EntityPathBase<Hall> {

    private static final long serialVersionUID = -756397160L;

    public static final QHall hall = new QHall("hall");

    public final com.sparta.projectblue.domain.common.entity.QBaseEntity _super = new com.sparta.projectblue.domain.common.entity.QBaseEntity(this);

    public final StringPath address = createString("address");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath name = createString("name");

    public final NumberPath<Integer> seats = createNumber("seats", Integer.class);

    public QHall(String variable) {
        super(Hall.class, forVariable(variable));
    }

    public QHall(Path<? extends Hall> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHall(PathMetadata metadata) {
        super(Hall.class, metadata);
    }

}

