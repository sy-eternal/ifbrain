package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QRouteInsurancePlan is a Querydsl query type for RouteInsurancePlan
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRouteInsurancePlan extends EntityPathBase<RouteInsurancePlan> {

    private static final long serialVersionUID = -2065866984L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRouteInsurancePlan routeInsurancePlan = new QRouteInsurancePlan("routeInsurancePlan");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final StringPath holderType = createString("holderType");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QInsuranceActivity insuranceActivity;

    public final StringPath insuranceDuration = createString("insuranceDuration");

    public final StringPath insuranceName = createString("insuranceName");

    public final NumberPath<Integer> personCount = createNumber("personCount", Integer.class);

    public final QRoute route;

    public final NumberPath<java.math.BigDecimal> subTotalAmount = createNumber("subTotalAmount", java.math.BigDecimal.class);

    public QRouteInsurancePlan(String variable) {
        this(RouteInsurancePlan.class, forVariable(variable), INITS);
    }

    public QRouteInsurancePlan(Path<? extends RouteInsurancePlan> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRouteInsurancePlan(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRouteInsurancePlan(PathMetadata<?> metadata, PathInits inits) {
        this(RouteInsurancePlan.class, metadata, inits);
    }

    public QRouteInsurancePlan(Class<? extends RouteInsurancePlan> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.insuranceActivity = inits.isInitialized("insuranceActivity") ? new QInsuranceActivity(forProperty("insuranceActivity"), inits.get("insuranceActivity")) : null;
        this.route = inits.isInitialized("route") ? new QRoute(forProperty("route"), inits.get("route")) : null;
    }

}

