package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QRouteSpotPlan is a Querydsl query type for RouteSpotPlan
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRouteSpotPlan extends EntityPathBase<RouteSpotPlan> {

    private static final long serialVersionUID = -1776464842L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRouteSpotPlan routeSpotPlan = new QRouteSpotPlan("routeSpotPlan");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath remark = createString("remark");

    public final QRouteDays routeDays;

    public final ListPath<RouteSpotPlanTicket, QRouteSpotPlanTicket> routeSpotPlanTickets = this.<RouteSpotPlanTicket, QRouteSpotPlanTicket>createList("routeSpotPlanTickets", RouteSpotPlanTicket.class, QRouteSpotPlanTicket.class, PathInits.DIRECT2);

    public final QSpot spot;

    public final NumberPath<java.math.BigDecimal> subtotalAmount = createNumber("subtotalAmount", java.math.BigDecimal.class);

    public final StringPath supplierNum = createString("supplierNum");

    public QRouteSpotPlan(String variable) {
        this(RouteSpotPlan.class, forVariable(variable), INITS);
    }

    public QRouteSpotPlan(Path<? extends RouteSpotPlan> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRouteSpotPlan(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRouteSpotPlan(PathMetadata<?> metadata, PathInits inits) {
        this(RouteSpotPlan.class, metadata, inits);
    }

    public QRouteSpotPlan(Class<? extends RouteSpotPlan> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.routeDays = inits.isInitialized("routeDays") ? new QRouteDays(forProperty("routeDays"), inits.get("routeDays")) : null;
        this.spot = inits.isInitialized("spot") ? new QSpot(forProperty("spot"), inits.get("spot")) : null;
    }

}

