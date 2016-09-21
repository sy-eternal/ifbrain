package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QRouteFilghtPlan is a Querydsl query type for RouteFilghtPlan
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRouteFilghtPlan extends EntityPathBase<RouteFilghtPlan> {

    private static final long serialVersionUID = 1349131134L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRouteFilghtPlan routeFilghtPlan = new QRouteFilghtPlan("routeFilghtPlan");

    public final StringPath airlineCodes = createString("airlineCodes");

    public final NumberPath<Integer> arrivalCityId = createNumber("arrivalCityId", Integer.class);

    public final DateTimePath<java.util.Date> arrivalDate = createDateTime("arrivalDate", java.util.Date.class);

    public final TimePath<java.sql.Time> arrivalTime = createTime("arrivalTime", java.sql.Time.class);

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> departureCityId = createNumber("departureCityId", Integer.class);

    public final DateTimePath<java.util.Date> departureDate = createDateTime("departureDate", java.util.Date.class);

    public final TimePath<java.sql.Time> departureTime = createTime("departureTime", java.sql.Time.class);

    public final ListPath<RouteFlightDetails, QRouteFlightDetails> flightDetails = this.<RouteFlightDetails, QRouteFlightDetails>createList("flightDetails", RouteFlightDetails.class, QRouteFlightDetails.class, PathInits.DIRECT2);

    public final StringPath flightType = createString("flightType");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> lengthseg = createNumber("lengthseg", Integer.class);

    public final NumberPath<java.math.BigDecimal> perCost = createNumber("perCost", java.math.BigDecimal.class);

    public final NumberPath<Integer> personalCount = createNumber("personalCount", Integer.class);

    public final NumberPath<Integer> personalType = createNumber("personalType", Integer.class);

    public final NumberPath<java.math.BigDecimal> price = createNumber("price", java.math.BigDecimal.class);

    public final QRouteDays routeDays;

    public final NumberPath<java.math.BigDecimal> subtotalAmount = createNumber("subtotalAmount", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> subtotalCost = createNumber("subtotalCost", java.math.BigDecimal.class);

    public final NumberPath<Integer> supplierId = createNumber("supplierId", Integer.class);

    public final StringPath supplierNum = createString("supplierNum");

    public final StringPath totalFlightTime = createString("totalFlightTime");

    public QRouteFilghtPlan(String variable) {
        this(RouteFilghtPlan.class, forVariable(variable), INITS);
    }

    public QRouteFilghtPlan(Path<? extends RouteFilghtPlan> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRouteFilghtPlan(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRouteFilghtPlan(PathMetadata<?> metadata, PathInits inits) {
        this(RouteFilghtPlan.class, metadata, inits);
    }

    public QRouteFilghtPlan(Class<? extends RouteFilghtPlan> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.routeDays = inits.isInitialized("routeDays") ? new QRouteDays(forProperty("routeDays"), inits.get("routeDays")) : null;
    }

}

