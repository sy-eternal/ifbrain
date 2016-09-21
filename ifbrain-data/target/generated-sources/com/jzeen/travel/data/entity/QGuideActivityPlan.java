package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QGuideActivityPlan is a Querydsl query type for GuideActivityPlan
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QGuideActivityPlan extends EntityPathBase<GuideActivityPlan> {

    private static final long serialVersionUID = 701767286L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGuideActivityPlan guideActivityPlan = new QGuideActivityPlan("guideActivityPlan");

    public final QCity city;

    public final QDatePlan datePlan;

    public final QGuideActivity guideActity;

    public final StringPath guideActivityType = createString("guideActivityType");

    public final NumberPath<Integer> guideCount = createNumber("guideCount", Integer.class);

    public final ListPath<GuideTypePlan, QGuideTypePlan> guideTypePlans = this.<GuideTypePlan, QGuideTypePlan>createList("guideTypePlans", GuideTypePlan.class, QGuideTypePlan.class, PathInits.DIRECT2);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<java.math.BigDecimal> subTotalAmount = createNumber("subTotalAmount", java.math.BigDecimal.class);

    public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

    public QGuideActivityPlan(String variable) {
        this(GuideActivityPlan.class, forVariable(variable), INITS);
    }

    public QGuideActivityPlan(Path<? extends GuideActivityPlan> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QGuideActivityPlan(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QGuideActivityPlan(PathMetadata<?> metadata, PathInits inits) {
        this(GuideActivityPlan.class, metadata, inits);
    }

    public QGuideActivityPlan(Class<? extends GuideActivityPlan> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.city = inits.isInitialized("city") ? new QCity(forProperty("city"), inits.get("city")) : null;
        this.datePlan = inits.isInitialized("datePlan") ? new QDatePlan(forProperty("datePlan"), inits.get("datePlan")) : null;
        this.guideActity = inits.isInitialized("guideActity") ? new QGuideActivity(forProperty("guideActity"), inits.get("guideActity")) : null;
    }

}

