package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QRouteHotelPlan is a Querydsl query type for RouteHotelPlan
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRouteHotelPlan extends EntityPathBase<RouteHotelPlan> {

    private static final long serialVersionUID = 1461632882L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRouteHotelPlan routeHotelPlan = new QRouteHotelPlan("routeHotelPlan");

    public final DateTimePath<java.util.Date> checkInDate = createDateTime("checkInDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> checkOutDate = createDateTime("checkOutDate", java.util.Date.class);

    public final QCity city;

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final QHotelActivity hotelActivity;

    public final StringPath hotelChaName = createString("hotelChaName");

    public final StringPath hotelEngName = createString("hotelEngName");

    public final ListPath<RouteHotelPlanRoom, QRouteHotelPlanRoom> hotelPlanRooms = this.<RouteHotelPlanRoom, QRouteHotelPlanRoom>createList("hotelPlanRooms", RouteHotelPlanRoom.class, QRouteHotelPlanRoom.class, PathInits.DIRECT2);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QRouteDays routeDays;

    public final NumberPath<java.math.BigDecimal> subtotalAmount = createNumber("subtotalAmount", java.math.BigDecimal.class);

    public QRouteHotelPlan(String variable) {
        this(RouteHotelPlan.class, forVariable(variable), INITS);
    }

    public QRouteHotelPlan(Path<? extends RouteHotelPlan> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRouteHotelPlan(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRouteHotelPlan(PathMetadata<?> metadata, PathInits inits) {
        this(RouteHotelPlan.class, metadata, inits);
    }

    public QRouteHotelPlan(Class<? extends RouteHotelPlan> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.city = inits.isInitialized("city") ? new QCity(forProperty("city"), inits.get("city")) : null;
        this.hotelActivity = inits.isInitialized("hotelActivity") ? new QHotelActivity(forProperty("hotelActivity"), inits.get("hotelActivity")) : null;
        this.routeDays = inits.isInitialized("routeDays") ? new QRouteDays(forProperty("routeDays"), inits.get("routeDays")) : null;
    }

}

