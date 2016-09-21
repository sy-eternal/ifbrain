package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QGuideCarManage is a Querydsl query type for GuideCarManage
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QGuideCarManage extends EntityPathBase<GuideCarManage> {

    private static final long serialVersionUID = 1746232187L;

    public static final QGuideCarManage guideCarManage = new QGuideCarManage("guideCarManage");

    public final StringPath classes = createString("classes");

    public final NumberPath<java.math.BigDecimal> cost = createNumber("cost", java.math.BigDecimal.class);

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<java.math.BigDecimal> excursionCost = createNumber("excursionCost", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> excursionPrice = createNumber("excursionPrice", java.math.BigDecimal.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<java.math.BigDecimal> salesPrice = createNumber("salesPrice", java.math.BigDecimal.class);

    public QGuideCarManage(String variable) {
        super(GuideCarManage.class, forVariable(variable));
    }

    public QGuideCarManage(Path<? extends GuideCarManage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGuideCarManage(PathMetadata<?> metadata) {
        super(GuideCarManage.class, metadata);
    }

}

