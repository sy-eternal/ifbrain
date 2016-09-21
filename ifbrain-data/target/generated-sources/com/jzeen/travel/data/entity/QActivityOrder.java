package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QActivityOrder is a Querydsl query type for ActivityOrder
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QActivityOrder extends EntityPathBase<ActivityOrder> {

    private static final long serialVersionUID = -140632831L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QActivityOrder activityOrder = new QActivityOrder("activityOrder");

    public final QActivity activity;

    public final NumberPath<java.math.BigDecimal> activityAmount = createNumber("activityAmount", java.math.BigDecimal.class);

    public final NumberPath<Integer> childNumber = createNumber("childNumber", Integer.class);

    public final StringPath contactMobile = createString("contactMobile");

    public final StringPath contactName = createString("contactName");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath orderNumber = createString("orderNumber");

    public final NumberPath<Integer> payStatus = createNumber("payStatus", Integer.class);

    public final DateTimePath<java.util.Date> payTime = createDateTime("payTime", java.util.Date.class);

    public QActivityOrder(String variable) {
        this(ActivityOrder.class, forVariable(variable), INITS);
    }

    public QActivityOrder(Path<? extends ActivityOrder> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QActivityOrder(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QActivityOrder(PathMetadata<?> metadata, PathInits inits) {
        this(ActivityOrder.class, metadata, inits);
    }

    public QActivityOrder(Class<? extends ActivityOrder> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.activity = inits.isInitialized("activity") ? new QActivity(forProperty("activity")) : null;
    }

}

