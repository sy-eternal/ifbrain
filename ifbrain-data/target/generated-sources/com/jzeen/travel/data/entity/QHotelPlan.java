package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QHotelPlan is a Querydsl query type for HotelPlan
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QHotelPlan extends EntityPathBase<HotelPlan> {

    private static final long serialVersionUID = 344637311L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHotelPlan hotelPlan = new QHotelPlan("hotelPlan");

    public final StringPath checkInDate = createString("checkInDate");

    public final StringPath checkOutDate = createString("checkOutDate");

    public final QCity city;

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final QDatePlan datePlan;

    public final QHotelActivity hotelActivity;

    public final StringPath hotelChaName = createString("hotelChaName");

    public final StringPath hotelEngName = createString("hotelEngName");

    public final ListPath<HotelPlanRoom, QHotelPlanRoom> hotelPlanRooms = this.<HotelPlanRoom, QHotelPlanRoom>createList("hotelPlanRooms", HotelPlanRoom.class, QHotelPlanRoom.class, PathInits.DIRECT2);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<java.math.BigDecimal> subtotalAmount = createNumber("subtotalAmount", java.math.BigDecimal.class);

    public QHotelPlan(String variable) {
        this(HotelPlan.class, forVariable(variable), INITS);
    }

    public QHotelPlan(Path<? extends HotelPlan> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QHotelPlan(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QHotelPlan(PathMetadata<?> metadata, PathInits inits) {
        this(HotelPlan.class, metadata, inits);
    }

    public QHotelPlan(Class<? extends HotelPlan> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.city = inits.isInitialized("city") ? new QCity(forProperty("city"), inits.get("city")) : null;
        this.datePlan = inits.isInitialized("datePlan") ? new QDatePlan(forProperty("datePlan"), inits.get("datePlan")) : null;
        this.hotelActivity = inits.isInitialized("hotelActivity") ? new QHotelActivity(forProperty("hotelActivity"), inits.get("hotelActivity")) : null;
    }

}

