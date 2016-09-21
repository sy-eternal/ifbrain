package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QGuideRate is a Querydsl query type for GuideRate
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QGuideRate extends EntityPathBase<GuideRate> {

    private static final long serialVersionUID = -1483598210L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGuideRate guideRate = new QGuideRate("guideRate");

    public final QGuideActivity guideActivity;

    public final NumberPath<java.math.BigDecimal> guideRateCost = createNumber("guideRateCost", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> guideRatePrice = createNumber("guideRatePrice", java.math.BigDecimal.class);

    public final StringPath guideType = createString("guideType");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

    public QGuideRate(String variable) {
        this(GuideRate.class, forVariable(variable), INITS);
    }

    public QGuideRate(Path<? extends GuideRate> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QGuideRate(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QGuideRate(PathMetadata<?> metadata, PathInits inits) {
        this(GuideRate.class, metadata, inits);
    }

    public QGuideRate(Class<? extends GuideRate> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.guideActivity = inits.isInitialized("guideActivity") ? new QGuideActivity(forProperty("guideActivity"), inits.get("guideActivity")) : null;
    }

}

