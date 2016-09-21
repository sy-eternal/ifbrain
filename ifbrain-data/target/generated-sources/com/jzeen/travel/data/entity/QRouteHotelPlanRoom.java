package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QRouteHotelPlanRoom is a Querydsl query type for RouteHotelPlanRoom
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRouteHotelPlanRoom extends EntityPathBase<RouteHotelPlanRoom> {

    private static final long serialVersionUID = 571779949L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRouteHotelPlanRoom routeHotelPlanRoom = new QRouteHotelPlanRoom("routeHotelPlanRoom");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final QHotelRoomType hotelRoomType;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> roomCount = createNumber("roomCount", Integer.class);

    public final QRouteHotelPlan routeHotelPlan;

    public final NumberPath<java.math.BigDecimal> salePrice = createNumber("salePrice", java.math.BigDecimal.class);

    public final StringPath supplierNum = createString("supplierNum");

    public QRouteHotelPlanRoom(String variable) {
        this(RouteHotelPlanRoom.class, forVariable(variable), INITS);
    }

    public QRouteHotelPlanRoom(Path<? extends RouteHotelPlanRoom> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRouteHotelPlanRoom(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRouteHotelPlanRoom(PathMetadata<?> metadata, PathInits inits) {
        this(RouteHotelPlanRoom.class, metadata, inits);
    }

    public QRouteHotelPlanRoom(Class<? extends RouteHotelPlanRoom> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hotelRoomType = inits.isInitialized("hotelRoomType") ? new QHotelRoomType(forProperty("hotelRoomType"), inits.get("hotelRoomType")) : null;
        this.routeHotelPlan = inits.isInitialized("routeHotelPlan") ? new QRouteHotelPlan(forProperty("routeHotelPlan"), inits.get("routeHotelPlan")) : null;
    }

}

