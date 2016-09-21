package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QStandardGuidePlan is a Querydsl query type for StandardGuidePlan
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QStandardGuidePlan extends EntityPathBase<StandardGuidePlan> {

    private static final long serialVersionUID = 1529411722L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStandardGuidePlan standardGuidePlan = new QStandardGuidePlan("standardGuidePlan");

    public final NumberPath<java.math.BigDecimal> commisionPercentage = createNumber("commisionPercentage", java.math.BigDecimal.class);

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final QDatePlan datePlan;

    public final StringPath guideName = createString("guideName");

    public final NumberPath<java.math.BigDecimal> guidePrice = createNumber("guidePrice", java.math.BigDecimal.class);

    public final StringPath hostCity = createString("hostCity");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final ListPath<StandardGuideOccupied, QStandardGuideOccupied> standardGuideOccupieds = this.<StandardGuideOccupied, QStandardGuideOccupied>createList("standardGuideOccupieds", StandardGuideOccupied.class, QStandardGuideOccupied.class, PathInits.DIRECT2);

    public QStandardGuidePlan(String variable) {
        this(StandardGuidePlan.class, forVariable(variable), INITS);
    }

    public QStandardGuidePlan(Path<? extends StandardGuidePlan> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStandardGuidePlan(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStandardGuidePlan(PathMetadata<?> metadata, PathInits inits) {
        this(StandardGuidePlan.class, metadata, inits);
    }

    public QStandardGuidePlan(Class<? extends StandardGuidePlan> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.datePlan = inits.isInitialized("datePlan") ? new QDatePlan(forProperty("datePlan"), inits.get("datePlan")) : null;
    }

}

