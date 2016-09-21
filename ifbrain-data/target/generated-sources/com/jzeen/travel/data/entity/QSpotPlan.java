package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSpotPlan is a Querydsl query type for SpotPlan
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSpotPlan extends EntityPathBase<SpotPlan> {

    private static final long serialVersionUID = 542807689L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSpotPlan spotPlan = new QSpotPlan("spotPlan");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final QDatePlan datePlan;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath remark = createString("remark");

    public final QSpot spot;

    public final ListPath<SpotPlanTicket, QSpotPlanTicket> spotPlanTicket = this.<SpotPlanTicket, QSpotPlanTicket>createList("spotPlanTicket", SpotPlanTicket.class, QSpotPlanTicket.class, PathInits.DIRECT2);

    public final NumberPath<java.math.BigDecimal> subtotalAmount = createNumber("subtotalAmount", java.math.BigDecimal.class);

    public final StringPath supplierNum = createString("supplierNum");

    public QSpotPlan(String variable) {
        this(SpotPlan.class, forVariable(variable), INITS);
    }

    public QSpotPlan(Path<? extends SpotPlan> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSpotPlan(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSpotPlan(PathMetadata<?> metadata, PathInits inits) {
        this(SpotPlan.class, metadata, inits);
    }

    public QSpotPlan(Class<? extends SpotPlan> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.datePlan = inits.isInitialized("datePlan") ? new QDatePlan(forProperty("datePlan"), inits.get("datePlan")) : null;
        this.spot = inits.isInitialized("spot") ? new QSpot(forProperty("spot"), inits.get("spot")) : null;
    }

}

