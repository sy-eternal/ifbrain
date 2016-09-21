package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QBuyPlan is a Querydsl query type for BuyPlan
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QBuyPlan extends EntityPathBase<BuyPlan> {

    private static final long serialVersionUID = 1291320273L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBuyPlan buyPlan = new QBuyPlan("buyPlan");

    public final QChild child;

    public final QCommodityType commodityType;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> planStatus = createNumber("planStatus", Integer.class);

    public final NumberPath<java.math.BigDecimal> price = createNumber("price", java.math.BigDecimal.class);

    public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

    public QBuyPlan(String variable) {
        this(BuyPlan.class, forVariable(variable), INITS);
    }

    public QBuyPlan(Path<? extends BuyPlan> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QBuyPlan(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QBuyPlan(PathMetadata<?> metadata, PathInits inits) {
        this(BuyPlan.class, metadata, inits);
    }

    public QBuyPlan(Class<? extends BuyPlan> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.child = inits.isInitialized("child") ? new QChild(forProperty("child"), inits.get("child")) : null;
        this.commodityType = inits.isInitialized("commodityType") ? new QCommodityType(forProperty("commodityType")) : null;
    }

}

