package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCustomCommodity is a Querydsl query type for CustomCommodity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCustomCommodity extends EntityPathBase<CustomCommodity> {

    private static final long serialVersionUID = -33158722L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCustomCommodity customCommodity = new QCustomCommodity("customCommodity");

    public final QChild child;

    public final QCommodityType commodityType;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QImage image;

    public final StringPath name = createString("name");

    public final NumberPath<java.math.BigDecimal> price = createNumber("price", java.math.BigDecimal.class);

    public final QUser user;

    public QCustomCommodity(String variable) {
        this(CustomCommodity.class, forVariable(variable), INITS);
    }

    public QCustomCommodity(Path<? extends CustomCommodity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCustomCommodity(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCustomCommodity(PathMetadata<?> metadata, PathInits inits) {
        this(CustomCommodity.class, metadata, inits);
    }

    public QCustomCommodity(Class<? extends CustomCommodity> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.child = inits.isInitialized("child") ? new QChild(forProperty("child"), inits.get("child")) : null;
        this.commodityType = inits.isInitialized("commodityType") ? new QCommodityType(forProperty("commodityType")) : null;
        this.image = inits.isInitialized("image") ? new QImage(forProperty("image"), inits.get("image")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

