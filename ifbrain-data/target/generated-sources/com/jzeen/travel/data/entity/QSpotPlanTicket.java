package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSpotPlanTicket is a Querydsl query type for SpotPlanTicket
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSpotPlanTicket extends EntityPathBase<SpotPlanTicket> {

    private static final long serialVersionUID = -1072356619L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSpotPlanTicket spotPlanTicket = new QSpotPlanTicket("spotPlanTicket");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> personCount = createNumber("personCount", Integer.class);

    public final NumberPath<java.math.BigDecimal> salePrice = createNumber("salePrice", java.math.BigDecimal.class);

    public final QSpotPlan spotPlan;

    public final QSpotTicketType spotTicketType;

    public final StringPath supplierNum = createString("supplierNum");

    public QSpotPlanTicket(String variable) {
        this(SpotPlanTicket.class, forVariable(variable), INITS);
    }

    public QSpotPlanTicket(Path<? extends SpotPlanTicket> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSpotPlanTicket(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSpotPlanTicket(PathMetadata<?> metadata, PathInits inits) {
        this(SpotPlanTicket.class, metadata, inits);
    }

    public QSpotPlanTicket(Class<? extends SpotPlanTicket> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.spotPlan = inits.isInitialized("spotPlan") ? new QSpotPlan(forProperty("spotPlan"), inits.get("spotPlan")) : null;
        this.spotTicketType = inits.isInitialized("spotTicketType") ? new QSpotTicketType(forProperty("spotTicketType"), inits.get("spotTicketType")) : null;
    }

}

