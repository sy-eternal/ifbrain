package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QRouteMarginPlan is a Querydsl query type for RouteMarginPlan
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRouteMarginPlan extends EntityPathBase<RouteMarginPlan> {

    private static final long serialVersionUID = -856681438L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRouteMarginPlan routeMarginPlan = new QRouteMarginPlan("routeMarginPlan");

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QMargin margin;

    public final NumberPath<java.math.BigDecimal> marginprice = createNumber("marginprice", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> margintotalprice = createNumber("margintotalprice", java.math.BigDecimal.class);

    public final QRoute route;

    public QRouteMarginPlan(String variable) {
        this(RouteMarginPlan.class, forVariable(variable), INITS);
    }

    public QRouteMarginPlan(Path<? extends RouteMarginPlan> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRouteMarginPlan(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRouteMarginPlan(PathMetadata<?> metadata, PathInits inits) {
        this(RouteMarginPlan.class, metadata, inits);
    }

    public QRouteMarginPlan(Class<? extends RouteMarginPlan> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.margin = inits.isInitialized("margin") ? new QMargin(forProperty("margin")) : null;
        this.route = inits.isInitialized("route") ? new QRoute(forProperty("route"), inits.get("route")) : null;
    }

}

