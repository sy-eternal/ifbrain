package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QShoppingmallCommodityImage is a Querydsl query type for ShoppingmallCommodityImage
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QShoppingmallCommodityImage extends EntityPathBase<ShoppingmallCommodityImage> {

    private static final long serialVersionUID = -1393952632L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QShoppingmallCommodityImage shoppingmallCommodityImage = new QShoppingmallCommodityImage("shoppingmallCommodityImage");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final StringPath fileName = createString("fileName");

    public final StringPath filePath = createString("filePath");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QShoppingmallCommodity shoppingmallCommodity;

    public QShoppingmallCommodityImage(String variable) {
        this(ShoppingmallCommodityImage.class, forVariable(variable), INITS);
    }

    public QShoppingmallCommodityImage(Path<? extends ShoppingmallCommodityImage> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QShoppingmallCommodityImage(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QShoppingmallCommodityImage(PathMetadata<?> metadata, PathInits inits) {
        this(ShoppingmallCommodityImage.class, metadata, inits);
    }

    public QShoppingmallCommodityImage(Class<? extends ShoppingmallCommodityImage> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.shoppingmallCommodity = inits.isInitialized("shoppingmallCommodity") ? new QShoppingmallCommodity(forProperty("shoppingmallCommodity"), inits.get("shoppingmallCommodity")) : null;
    }

}

