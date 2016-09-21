package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QRouteSpotPlanTicket is a Querydsl query type for RouteSpotPlanTicket
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRouteSpotPlanTicket extends EntityPathBase<RouteSpotPlanTicket> {

    private static final long serialVersionUID = -472767774L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRouteSpotPlanTicket routeSpotPlanTicket = new QRouteSpotPlanTicket("routeSpotPlanTicket");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> personCount = createNumber("personCount", Integer.class);

    public final QRouteSpotPlan routeSpotPlan;

    public final NumberPath<java.math.BigDecimal> salePrice = createNumber("salePrice", java.math.BigDecimal.class);

    public final QSpotTicketType spotTicketType;

    public final StringPath supplierNum = createString("supplierNum");

    public QRouteSpotPlanTicket(String variable) {
        this(RouteSpotPlanTicket.class, forVariable(variable), INITS);
    }

    public QRouteSpotPlanTicket(Path<? extends RouteSpotPlanTicket> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRouteSpotPlanTicket(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRouteSpotPlanTicket(PathMetadata<?> metadata, PathInits inits) {
        this(RouteSpotPlanTicket.class, metadata, inits);
    }

    public QRouteSpotPlanTicket(Class<? extends RouteSpotPlanTicket> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.routeSpotPlan = inits.isInitialized("routeSpotPlan") ? new QRouteSpotPlan(forProperty("routeSpotPlan"), inits.get("routeSpotPlan")) : null;
        this.spotTicketType = inits.isInitialized("spotTicketType") ? new QSpotTicketType(forProperty("spotTicketType"), inits.get("spotTicketType")) : null;
    }

}

