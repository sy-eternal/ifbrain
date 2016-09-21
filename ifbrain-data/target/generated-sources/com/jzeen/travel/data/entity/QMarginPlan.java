package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QMarginPlan is a Querydsl query type for MarginPlan
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMarginPlan extends EntityPathBase<MarginPlan> {

    private static final long serialVersionUID = -1123805771L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMarginPlan marginPlan = new QMarginPlan("marginPlan");

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QMargin margin;

    public final NumberPath<java.math.BigDecimal> marginprice = createNumber("marginprice", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> margintotalprice = createNumber("margintotalprice", java.math.BigDecimal.class);

    public final QOrder order;

    public QMarginPlan(String variable) {
        this(MarginPlan.class, forVariable(variable), INITS);
    }

    public QMarginPlan(Path<? extends MarginPlan> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMarginPlan(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMarginPlan(PathMetadata<?> metadata, PathInits inits) {
        this(MarginPlan.class, metadata, inits);
    }

    public QMarginPlan(Class<? extends MarginPlan> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.margin = inits.isInitialized("margin") ? new QMargin(forProperty("margin")) : null;
        this.order = inits.isInitialized("order") ? new QOrder(forProperty("order"), inits.get("order")) : null;
    }

}

