package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QCommodityType is a Querydsl query type for CommodityType
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCommodityType extends EntityPathBase<CommodityType> {

    private static final long serialVersionUID = -1905417335L;

    public static final QCommodityType commodityType = new QCommodityType("commodityType");

    public final StringPath filepath = createString("filepath");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public QCommodityType(String variable) {
        super(CommodityType.class, forVariable(variable));
    }

    public QCommodityType(Path<? extends CommodityType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCommodityType(PathMetadata<?> metadata) {
        super(CommodityType.class, metadata);
    }

}

