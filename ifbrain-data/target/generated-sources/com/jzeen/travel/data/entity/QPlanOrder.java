package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QPlanOrder is a Querydsl query type for PlanOrder
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPlanOrder extends EntityPathBase<PlanOrder> {

    private static final long serialVersionUID = -1614375385L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPlanOrder planOrder = new QPlanOrder("planOrder");

    public final NumberPath<java.math.BigDecimal> amount = createNumber("amount", java.math.BigDecimal.class);

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QOrder order;

    public final StringPath planOfferName = createString("planOfferName");

    public final NumberPath<java.math.BigDecimal> serviceAmount = createNumber("serviceAmount", java.math.BigDecimal.class);

    public QPlanOrder(String variable) {
        this(PlanOrder.class, forVariable(variable), INITS);
    }

    public QPlanOrder(Path<? extends PlanOrder> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPlanOrder(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPlanOrder(PathMetadata<?> metadata, PathInits inits) {
        this(PlanOrder.class, metadata, inits);
    }

    public QPlanOrder(Class<? extends PlanOrder> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.order = inits.isInitialized("order") ? new QOrder(forProperty("order"), inits.get("order")) : null;
    }

}

