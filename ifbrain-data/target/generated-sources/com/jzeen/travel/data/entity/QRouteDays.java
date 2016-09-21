package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QRouteDays is a Querydsl query type for RouteDays
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRouteDays extends EntityPathBase<RouteDays> {

    private static final long serialVersionUID = 760070850L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRouteDays routeDays = new QRouteDays("routeDays");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> dayId = createNumber("dayId", Integer.class);

    public final ListPath<RouteFilghtPlan, QRouteFilghtPlan> filghtPlan = this.<RouteFilghtPlan, QRouteFilghtPlan>createList("filghtPlan", RouteFilghtPlan.class, QRouteFilghtPlan.class, PathInits.DIRECT2);

    public final ListPath<RouteGuideActivityPlan, QRouteGuideActivityPlan> guideActivityPlans = this.<RouteGuideActivityPlan, QRouteGuideActivityPlan>createList("guideActivityPlans", RouteGuideActivityPlan.class, QRouteGuideActivityPlan.class, PathInits.DIRECT2);

    public final ListPath<RouteHotelPlan, QRouteHotelPlan> hotelPlan = this.<RouteHotelPlan, QRouteHotelPlan>createList("hotelPlan", RouteHotelPlan.class, QRouteHotelPlan.class, PathInits.DIRECT2);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final ListPath<RouteRentalPlan, QRouteRentalPlan> rentalPlan = this.<RouteRentalPlan, QRouteRentalPlan>createList("rentalPlan", RouteRentalPlan.class, QRouteRentalPlan.class, PathInits.DIRECT2);

    public final QRoute route;

    public final ListPath<RouteSpotPlan, QRouteSpotPlan> spotPlan = this.<RouteSpotPlan, QRouteSpotPlan>createList("spotPlan", RouteSpotPlan.class, QRouteSpotPlan.class, PathInits.DIRECT2);

    public QRouteDays(String variable) {
        this(RouteDays.class, forVariable(variable), INITS);
    }

    public QRouteDays(Path<? extends RouteDays> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRouteDays(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRouteDays(PathMetadata<?> metadata, PathInits inits) {
        this(RouteDays.class, metadata, inits);
    }

    public QRouteDays(Class<? extends RouteDays> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.route = inits.isInitialized("route") ? new QRoute(forProperty("route"), inits.get("route")) : null;
    }

}

