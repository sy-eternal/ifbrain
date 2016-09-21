package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QGuideTypePlan is a Querydsl query type for GuideTypePlan
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QGuideTypePlan extends EntityPathBase<GuideTypePlan> {

    private static final long serialVersionUID = -1976488927L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGuideTypePlan guideTypePlan = new QGuideTypePlan("guideTypePlan");

    public final NumberPath<Integer> appointedStatus = createNumber("appointedStatus", Integer.class);

    public final QGuideActivityPlan guideActivityPlan;

    public final NumberPath<java.math.BigDecimal> guideCost = createNumber("guideCost", java.math.BigDecimal.class);

    public final NumberPath<Integer> guideCount = createNumber("guideCount", Integer.class);

    public final ListPath<GuideGuidetypePlanRelate, QGuideGuidetypePlanRelate> guideGuidetypePlanRelate = this.<GuideGuidetypePlanRelate, QGuideGuidetypePlanRelate>createList("guideGuidetypePlanRelate", GuideGuidetypePlanRelate.class, QGuideGuidetypePlanRelate.class, PathInits.DIRECT2);

    public final NumberPath<java.math.BigDecimal> guidePrice = createNumber("guidePrice", java.math.BigDecimal.class);

    public final QGuideRate guideRate;

    public final StringPath guideType = createString("guideType");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

    public QGuideTypePlan(String variable) {
        this(GuideTypePlan.class, forVariable(variable), INITS);
    }

    public QGuideTypePlan(Path<? extends GuideTypePlan> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QGuideTypePlan(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QGuideTypePlan(PathMetadata<?> metadata, PathInits inits) {
        this(GuideTypePlan.class, metadata, inits);
    }

    public QGuideTypePlan(Class<? extends GuideTypePlan> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.guideActivityPlan = inits.isInitialized("guideActivityPlan") ? new QGuideActivityPlan(forProperty("guideActivityPlan"), inits.get("guideActivityPlan")) : null;
        this.guideRate = inits.isInitialized("guideRate") ? new QGuideRate(forProperty("guideRate"), inits.get("guideRate")) : null;
    }

}

