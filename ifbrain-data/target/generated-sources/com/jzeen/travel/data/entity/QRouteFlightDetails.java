package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QRouteFlightDetails is a Querydsl query type for RouteFlightDetails
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRouteFlightDetails extends EntityPathBase<RouteFlightDetails> {

    private static final long serialVersionUID = -1098457689L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRouteFlightDetails routeFlightDetails = new QRouteFlightDetails("routeFlightDetails");

    public final StringPath arrivalAirportCode = createString("arrivalAirportCode");

    public final NumberPath<Integer> arrivalCityId = createNumber("arrivalCityId", Integer.class);

    public final DateTimePath<java.util.Date> arrivalDate = createDateTime("arrivalDate", java.util.Date.class);

    public final StringPath arrivalTerminalId = createString("arrivalTerminalId");

    public final TimePath<java.sql.Time> arrivalTime = createTime("arrivalTime", java.sql.Time.class);

    public final StringPath cabin = createString("cabin");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final StringPath departureAirportCode = createString("departureAirportCode");

    public final NumberPath<Integer> departureCityId = createNumber("departureCityId", Integer.class);

    public final DateTimePath<java.util.Date> departureDate = createDateTime("departureDate", java.util.Date.class);

    public final TimePath<java.sql.Time> departureTime = createTime("departureTime", java.sql.Time.class);

    public final StringPath departureTrminalId = createString("departureTrminalId");

    public final StringPath flightNumber = createString("flightNumber");

    public final StringPath flightType = createString("flightType");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath layoverTime = createString("layoverTime");

    public final QRouteFilghtPlan routeFilghtPlan;

    public final StringPath travelTime = createString("travelTime");

    public QRouteFlightDetails(String variable) {
        this(RouteFlightDetails.class, forVariable(variable), INITS);
    }

    public QRouteFlightDetails(Path<? extends RouteFlightDetails> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRouteFlightDetails(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRouteFlightDetails(PathMetadata<?> metadata, PathInits inits) {
        this(RouteFlightDetails.class, metadata, inits);
    }

    public QRouteFlightDetails(Class<? extends RouteFlightDetails> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.routeFilghtPlan = inits.isInitialized("routeFilghtPlan") ? new QRouteFilghtPlan(forProperty("routeFilghtPlan"), inits.get("routeFilghtPlan")) : null;
    }

}

