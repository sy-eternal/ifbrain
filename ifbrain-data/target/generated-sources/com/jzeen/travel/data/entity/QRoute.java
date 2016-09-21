package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QRoute is a Querydsl query type for Route
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRoute extends EntityPathBase<Route> {

    private static final long serialVersionUID = -1651098677L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRoute route = new QRoute("route");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QImage image;

    public final ListPath<RouteDays, QRouteDays> routeDays = this.<RouteDays, QRouteDays>createList("routeDays", RouteDays.class, QRouteDays.class, PathInits.DIRECT2);

    public final ListPath<RouteInsurancePlan, QRouteInsurancePlan> routeInsurancePlans = this.<RouteInsurancePlan, QRouteInsurancePlan>createList("routeInsurancePlans", RouteInsurancePlan.class, QRouteInsurancePlan.class, PathInits.DIRECT2);

    public final ListPath<RouteMarginPlan, QRouteMarginPlan> routeMarginPlans = this.<RouteMarginPlan, QRouteMarginPlan>createList("routeMarginPlans", RouteMarginPlan.class, QRouteMarginPlan.class, PathInits.DIRECT2);

    public final StringPath routeName = createString("routeName");

    public final StringPath routeNum = createString("routeNum");

    public final ListPath<RouteTag, QRouteTag> routeTages = this.<RouteTag, QRouteTag>createList("routeTages", RouteTag.class, QRouteTag.class, PathInits.DIRECT2);

    public QRoute(String variable) {
        this(Route.class, forVariable(variable), INITS);
    }

    public QRoute(Path<? extends Route> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRoute(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRoute(PathMetadata<?> metadata, PathInits inits) {
        this(Route.class, metadata, inits);
    }

    public QRoute(Class<? extends Route> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.image = inits.isInitialized("image") ? new QImage(forProperty("image"), inits.get("image")) : null;
    }

}

