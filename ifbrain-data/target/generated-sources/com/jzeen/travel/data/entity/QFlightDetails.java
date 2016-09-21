package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QFlightDetails is a Querydsl query type for FlightDetails
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QFlightDetails extends EntityPathBase<FlightDetails> {

    private static final long serialVersionUID = -425062604L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFlightDetails flightDetails = new QFlightDetails("flightDetails");

    public final StringPath arrivalAirportCode = createString("arrivalAirportCode");

    public final StringPath arrivalCityId = createString("arrivalCityId");

    public final StringPath arrivalDate = createString("arrivalDate");

    public final StringPath arrivalTerminalId = createString("arrivalTerminalId");

    public final StringPath arrivalTime = createString("arrivalTime");

    public final StringPath cabin = createString("cabin");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final StringPath departureAirportCode = createString("departureAirportCode");

    public final StringPath departureCityId = createString("departureCityId");

    public final StringPath departureDate = createString("departureDate");

    public final StringPath departureTime = createString("departureTime");

    public final StringPath departureTrminalId = createString("departureTrminalId");

    public final QFilghtPlan filghtPlan;

    public final StringPath flightNumber = createString("flightNumber");

    public final StringPath flightType = createString("flightType");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath layoverTime = createString("layoverTime");

    public final StringPath travelTime = createString("travelTime");

    public QFlightDetails(String variable) {
        this(FlightDetails.class, forVariable(variable), INITS);
    }

    public QFlightDetails(Path<? extends FlightDetails> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QFlightDetails(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QFlightDetails(PathMetadata<?> metadata, PathInits inits) {
        this(FlightDetails.class, metadata, inits);
    }

    public QFlightDetails(Class<? extends FlightDetails> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.filghtPlan = inits.isInitialized("filghtPlan") ? new QFilghtPlan(forProperty("filghtPlan"), inits.get("filghtPlan")) : null;
    }

}

