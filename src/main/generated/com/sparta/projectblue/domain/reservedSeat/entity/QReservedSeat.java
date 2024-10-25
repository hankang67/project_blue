package com.sparta.projectblue.domain.reservedSeat.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReservedSeat is a Querydsl query type for ReservedSeat
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReservedSeat extends EntityPathBase<ReservedSeat> {

    private static final long serialVersionUID = -1758444608L;

    public static final QReservedSeat reservedSeat = new QReservedSeat("reservedSeat");

    public final com.sparta.projectblue.domain.common.entity.QBaseEntity _super = new com.sparta.projectblue.domain.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final NumberPath<Long> reservationId = createNumber("reservationId", Long.class);

    public final NumberPath<Long> roundId = createNumber("roundId", Long.class);

    public final NumberPath<Integer> seatNumber = createNumber("seatNumber", Integer.class);

    public QReservedSeat(String variable) {
        super(ReservedSeat.class, forVariable(variable));
    }

    public QReservedSeat(Path<? extends ReservedSeat> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReservedSeat(PathMetadata metadata) {
        super(ReservedSeat.class, metadata);
    }

}

