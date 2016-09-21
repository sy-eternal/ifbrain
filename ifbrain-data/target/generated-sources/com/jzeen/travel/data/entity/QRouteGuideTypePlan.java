package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QRouteGuideTypePlan is a Querydsl query type for RouteGuideTypePlan
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRouteGuideTypePlan extends EntityPathBase<RouteGuideTypePlan> {

    private static final long serialVersionUID = 1645083284L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRouteGuideTypePlan routeGuideTypePlan = new QRouteGuideTypePlan("routeGuideTypePlan");

    public final NumberPath<Integer> appointedStatus = createNumber("appointedStatus", Integer.class);

    public final NumberPath<java.math.BigDecimal> guideCost = createNumber("guideCost", java.math.BigDecimal.class);

    public final NumberPath<Integer> guideCount = createNumber("guideCount", Integer.class);

    public final NumberPath<java.math.BigDecimal> guidePrice = createNumber("guidePrice", java.math.BigDecimal.class);

    public final QGuideRate guideRate;

    public final StringPath guideType = createString("guideType");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QRouteGuideActivityPlan routeGuideActivityPlan;

    public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

    public QRouteGuideTypePlan(String variable) {
        this(RouteGuideTypePlan.class, forVariable(variable), INITS);
    }

    public QRouteGuideTypePlan(Path<? extends RouteGuideTypePlan> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRouteGuideTypePlan(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRouteGuideTypePlan(PathMetadata<?> metadata, PathInits inits) {
        this(RouteGuideTypePlan.class, metadata, inits);
    }

    public QRouteGuideTypePlan(Class<? extends RouteGuideTypePlan> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.guideRate = inits.isInitialized("guideRate") ? new QGuideRate(forProperty("guideRate"), inits.get("guideRate")) : null;
        this.routeGuideActivityPlan = inits.isInitialized("routeGuideActivityPlan") ? new QRouteGuideActivityPlan(forProperty("routeGuideActivityPlan"), inits.get("routeGuideActivityPlan")) : null;
    }

}

