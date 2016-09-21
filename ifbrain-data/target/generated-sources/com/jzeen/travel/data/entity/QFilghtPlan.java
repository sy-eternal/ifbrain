package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QFilghtPlan is a Querydsl query type for FilghtPlan
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QFilghtPlan extends EntityPathBase<FilghtPlan> {

    private static final long serialVersionUID = 1082006801L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFilghtPlan filghtPlan = new QFilghtPlan("filghtPlan");

    public final StringPath airlineCodes = createString("airlineCodes");

    public final StringPath arrivalCityId = createString("arrivalCityId");

    public final StringPath arrivalDate = createString("arrivalDate");

    public final StringPath arrivalTime = createString("arrivalTime");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final QDatePlan datePlan;

    public final StringPath departureCityId = createString("departureCityId");

    public final StringPath departureDate = createString("departureDate");

    public final StringPath departureTime = createString("departureTime");

    public final ListPath<FlightDetails, QFlightDetails> flightDetails = this.<FlightDetails, QFlightDetails>createList("flightDetails", FlightDetails.class, QFlightDetails.class, PathInits.DIRECT2);

    public final StringPath flightType = createString("flightType");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<java.math.BigDecimal> perCost = createNumber("perCost", java.math.BigDecimal.class);

    public final NumberPath<Integer> personalCount = createNumber("personalCount", Integer.class);

    public final NumberPath<Integer> personalType = createNumber("personalType", Integer.class);

    public final NumberPath<java.math.BigDecimal> price = createNumber("price", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> subtotalAmount = createNumber("subtotalAmount", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> subtotalCost = createNumber("subtotalCost", java.math.BigDecimal.class);

    public final NumberPath<Integer> supplierId = createNumber("supplierId", Integer.class);

    public final StringPath supplierNum = createString("supplierNum");

    public final StringPath totalFlightTime = createString("totalFlightTime");

    public final NumberPath<Integer> transferFrequency = createNumber("transferFrequency", Integer.class);

    public QFilghtPlan(String variable) {
        this(FilghtPlan.class, forVariable(variable), INITS);
    }

    public QFilghtPlan(Path<? extends FilghtPlan> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QFilghtPlan(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QFilghtPlan(PathMetadata<?> metadata, PathInits inits) {
        this(FilghtPlan.class, metadata, inits);
    }

    public QFilghtPlan(Class<? extends FilghtPlan> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.datePlan = inits.isInitialized("datePlan") ? new QDatePlan(forProperty("datePlan"), inits.get("datePlan")) : null;
    }

}

