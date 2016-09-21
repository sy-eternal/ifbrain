package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QRouteGuideActivityPlan is a Querydsl query type for RouteGuideActivityPlan
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRouteGuideActivityPlan extends EntityPathBase<RouteGuideActivityPlan> {

    private static final long serialVersionUID = 284064617L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRouteGuideActivityPlan routeGuideActivityPlan = new QRouteGuideActivityPlan("routeGuideActivityPlan");

    public final QCity city;

    public final QGuideActivity guideActity;

    public final StringPath guideActivityType = createString("guideActivityType");

    public final NumberPath<Integer> guideCount = createNumber("guideCount", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QRouteDays routeDays;

    public final ListPath<RouteGuideTypePlan, QRouteGuideTypePlan> routeGuideTypePlans = this.<RouteGuideTypePlan, QRouteGuideTypePlan>createList("routeGuideTypePlans", RouteGuideTypePlan.class, QRouteGuideTypePlan.class, PathInits.DIRECT2);

    public final NumberPath<java.math.BigDecimal> subTotalAmount = createNumber("subTotalAmount", java.math.BigDecimal.class);

    public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

    public QRouteGuideActivityPlan(String variable) {
        this(RouteGuideActivityPlan.class, forVariable(variable), INITS);
    }

    public QRouteGuideActivityPlan(Path<? extends RouteGuideActivityPlan> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRouteGuideActivityPlan(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRouteGuideActivityPlan(PathMetadata<?> metadata, PathInits inits) {
        this(RouteGuideActivityPlan.class, metadata, inits);
    }

    public QRouteGuideActivityPlan(Class<? extends RouteGuideActivityPlan> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.city = inits.isInitialized("city") ? new QCity(forProperty("city"), inits.get("city")) : null;
        this.guideActity = inits.isInitialized("guideActity") ? new QGuideActivity(forProperty("guideActity"), inits.get("guideActity")) : null;
        this.routeDays = inits.isInitialized("routeDays") ? new QRouteDays(forProperty("routeDays"), inits.get("routeDays")) : null;
    }

}

