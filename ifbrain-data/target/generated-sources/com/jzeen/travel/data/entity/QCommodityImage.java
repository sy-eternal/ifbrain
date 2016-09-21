package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QCommodityImage is a Querydsl query type for CommodityImage
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCommodityImage extends EntityPathBase<CommodityImage> {

    private static final long serialVersionUID = 1051074284L;

    public static final QCommodityImage commodityImage = new QCommodityImage("commodityImage");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final StringPath fileName = createString("fileName");

    public final StringPath filePath = createString("filePath");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

    public QCommodityImage(String variable) {
        super(CommodityImage.class, forVariable(variable));
    }

    public QCommodityImage(Path<? extends CommodityImage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCommodityImage(PathMetadata<?> metadata) {
        super(CommodityImage.class, metadata);
    }

}

