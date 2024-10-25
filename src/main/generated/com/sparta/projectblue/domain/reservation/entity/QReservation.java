package com.sparta.projectblue.domain.reservation.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReservation is a Querydsl query type for Reservation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReservation extends EntityPathBase<Reservation> {

    private static final long serialVersionUID = -487212532L;

    public static final QReservation reservation = new QReservation("reservation");

    public final com.sparta.projectblue.domain.common.entity.QBaseEntity _super = new com.sparta.projectblue.domain.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final NumberPath<Long> paymentId = createNumber("paymentId", Long.class);

    public final NumberPath<Long> performanceId = createNumber("performanceId", Long.class);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final NumberPath<Long> roundId = createNumber("roundId", Long.class);

    public final EnumPath<com.sparta.projectblue.domain.common.enums.ReservationStatus> status = createEnum("status", com.sparta.projectblue.domain.common.enums.ReservationStatus.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QReservation(String variable) {
        super(Reservation.class, forVariable(variable));
    }

    public QReservation(Path<? extends Reservation> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReservation(PathMetadata metadata) {
        super(Reservation.class, metadata);
    }

}

