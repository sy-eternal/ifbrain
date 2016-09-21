package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCommodity is a Querydsl query type for Commodity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCommodity extends EntityPathBase<Commodity> {

    private static final long serialVersionUID = -893186129L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCommodity commodity = new QCommodity("commodity");

    public final QCommodityType commodityType;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QImage image;

    public final StringPath name = createString("name");

    public final NumberPath<java.math.BigDecimal> price = createNumber("price", java.math.BigDecimal.class);

    public QCommodity(String variable) {
        this(Commodity.class, forVariable(variable), INITS);
    }

    public QCommodity(Path<? extends Commodity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCommodity(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCommodity(PathMetadata<?> metadata, PathInits inits) {
        this(Commodity.class, metadata, inits);
    }

    public QCommodity(Class<? extends Commodity> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.commodityType = inits.isInitialized("commodityType") ? new QCommodityType(forProperty("commodityType")) : null;
        this.image = inits.isInitialized("image") ? new QImage(forProperty("image"), inits.get("image")) : null;
    }

}

