package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QShopHistory is a Querydsl query type for ShopHistory
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QShopHistory extends EntityPathBase<ShopHistory> {

    private static final long serialVersionUID = 548757696L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QShopHistory shopHistory = new QShopHistory("shopHistory");

    public final NumberPath<Integer> buyNumber = createNumber("buyNumber", Integer.class);

    public final QChild child;

    public final QCommodityType commodityType;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QImage image;

    public final StringPath name = createString("name");

    public final NumberPath<java.math.BigDecimal> price = createNumber("price", java.math.BigDecimal.class);

    public final DateTimePath<java.util.Date> startTime = createDateTime("startTime", java.util.Date.class);

    public final QUser user;

    public QShopHistory(String variable) {
        this(ShopHistory.class, forVariable(variable), INITS);
    }

    public QShopHistory(Path<? extends ShopHistory> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QShopHistory(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QShopHistory(PathMetadata<?> metadata, PathInits inits) {
        this(ShopHistory.class, metadata, inits);
    }

    public QShopHistory(Class<? extends ShopHistory> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.child = inits.isInitialized("child") ? new QChild(forProperty("child"), inits.get("child")) : null;
        this.commodityType = inits.isInitialized("commodityType") ? new QCommodityType(forProperty("commodityType")) : null;
        this.image = inits.isInitialized("image") ? new QImage(forProperty("image"), inits.get("image")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

