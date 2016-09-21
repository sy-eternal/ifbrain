package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QExcursionGuidePlan is a Querydsl query type for ExcursionGuidePlan
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QExcursionGuidePlan extends EntityPathBase<ExcursionGuidePlan> {

    private static final long serialVersionUID = 2114666305L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QExcursionGuidePlan excursionGuidePlan = new QExcursionGuidePlan("excursionGuidePlan");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final QDatePlan datePlan;

    public final ListPath<ExcursionGuideOccupied, QExcursionGuideOccupied> excursionGuideOccupieds = this.<ExcursionGuideOccupied, QExcursionGuideOccupied>createList("excursionGuideOccupieds", ExcursionGuideOccupied.class, QExcursionGuideOccupied.class, PathInits.DIRECT2);

    public final NumberPath<java.math.BigDecimal> guideCarPrice = createNumber("guideCarPrice", java.math.BigDecimal.class);

    public final NumberPath<Integer> guideCount = createNumber("guideCount", Integer.class);

    public final NumberPath<java.math.BigDecimal> guidePrice = createNumber("guidePrice", java.math.BigDecimal.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final BooleanPath startOrEnd = createBoolean("startOrEnd");

    public QExcursionGuidePlan(String variable) {
        this(ExcursionGuidePlan.class, forVariable(variable), INITS);
    }

    public QExcursionGuidePlan(Path<? extends ExcursionGuidePlan> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QExcursionGuidePlan(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QExcursionGuidePlan(PathMetadata<?> metadata, PathInits inits) {
        this(ExcursionGuidePlan.class, metadata, inits);
    }

    public QExcursionGuidePlan(Class<? extends ExcursionGuidePlan> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.datePlan = inits.isInitialized("datePlan") ? new QDatePlan(forProperty("datePlan"), inits.get("datePlan")) : null;
    }

}

