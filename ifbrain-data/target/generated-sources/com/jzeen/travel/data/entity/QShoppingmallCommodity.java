package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QShoppingmallCommodity is a Querydsl query type for ShoppingmallCommodity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QShoppingmallCommodity extends EntityPathBase<ShoppingmallCommodity> {

    private static final long serialVersionUID = -1871928173L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QShoppingmallCommodity shoppingmallCommodity = new QShoppingmallCommodity("shoppingmallCommodity");

    public final QCommodityMall commodityMall;

    public final StringPath commodityName = createString("commodityName");

    public final NumberPath<Integer> commodityQuantity = createNumber("commodityQuantity", Integer.class);

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final QDemandLevel demandLevel;

    public final StringPath description = createString("description");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<java.math.BigDecimal> price = createNumber("price", java.math.BigDecimal.class);

    public final ListPath<ShoppingmallCommodityImage, QShoppingmallCommodityImage> shoppingmallCommodityImage = this.<ShoppingmallCommodityImage, QShoppingmallCommodityImage>createList("shoppingmallCommodityImage", ShoppingmallCommodityImage.class, QShoppingmallCommodityImage.class, PathInits.DIRECT2);

    public QShoppingmallCommodity(String variable) {
        this(ShoppingmallCommodity.class, forVariable(variable), INITS);
    }

    public QShoppingmallCommodity(Path<? extends ShoppingmallCommodity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QShoppingmallCommodity(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QShoppingmallCommodity(PathMetadata<?> metadata, PathInits inits) {
        this(ShoppingmallCommodity.class, metadata, inits);
    }

    public QShoppingmallCommodity(Class<? extends ShoppingmallCommodity> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.commodityMall = inits.isInitialized("commodityMall") ? new QCommodityMall(forProperty("commodityMall")) : null;
        this.demandLevel = inits.isInitialized("demandLevel") ? new QDemandLevel(forProperty("demandLevel")) : null;
    }

}

