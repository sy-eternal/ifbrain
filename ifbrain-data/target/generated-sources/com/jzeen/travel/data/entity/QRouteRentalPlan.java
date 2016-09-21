package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QRouteRentalPlan is a Querydsl query type for RouteRentalPlan
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRouteRentalPlan extends EntityPathBase<RouteRentalPlan> {

    private static final long serialVersionUID = -438673256L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRouteRentalPlan routeRentalPlan = new QRouteRentalPlan("routeRentalPlan");

    public final NumberPath<java.math.BigDecimal> carprice = createNumber("carprice", java.math.BigDecimal.class);

    public final StringPath cartype = createString("cartype");

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QRouteDays routeDays;

    public final QRentalCar specialcar;

    public final NumberPath<java.math.BigDecimal> subtotalamount = createNumber("subtotalamount", java.math.BigDecimal.class);

    public final StringPath supplierNum = createString("supplierNum");

    public QRouteRentalPlan(String variable) {
        this(RouteRentalPlan.class, forVariable(variable), INITS);
    }

    public QRouteRentalPlan(Path<? extends RouteRentalPlan> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRouteRentalPlan(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRouteRentalPlan(PathMetadata<?> metadata, PathInits inits) {
        this(RouteRentalPlan.class, metadata, inits);
    }

    public QRouteRentalPlan(Class<? extends RouteRentalPlan> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.routeDays = inits.isInitialized("routeDays") ? new QRouteDays(forProperty("routeDays"), inits.get("routeDays")) : null;
        this.specialcar = inits.isInitialized("specialcar") ? new QRentalCar(forProperty("specialcar"), inits.get("specialcar")) : null;
    }

}

