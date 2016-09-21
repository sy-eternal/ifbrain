package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCommodityMall is a Querydsl query type for CommodityMall
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCommodityMall extends EntityPathBase<CommodityMall> {

    private static final long serialVersionUID = -1905649053L;

    public static final QCommodityMall commodityMall = new QCommodityMall("commodityMall");

    public final StringPath commodityType = createString("commodityType");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final ListPath<ShoppingmallCommodity, QShoppingmallCommodity> shoppingmallCommodity = this.<ShoppingmallCommodity, QShoppingmallCommodity>createList("shoppingmallCommodity", ShoppingmallCommodity.class, QShoppingmallCommodity.class, PathInits.DIRECT2);

    public final StringPath type = createString("type");

    public QCommodityMall(String variable) {
        super(CommodityMall.class, forVariable(variable));
    }

    public QCommodityMall(Path<? extends CommodityMall> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCommodityMall(PathMetadata<?> metadata) {
        super(CommodityMall.class, metadata);
    }

}

