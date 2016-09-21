package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QExcursionGuideOccupied is a Querydsl query type for ExcursionGuideOccupied
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QExcursionGuideOccupied extends EntityPathBase<ExcursionGuideOccupied> {

    private static final long serialVersionUID = -1141945674L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QExcursionGuideOccupied excursionGuideOccupied = new QExcursionGuideOccupied("excursionGuideOccupied");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final QExcursionGuide excursionGuide;

    public final QExcursionGuidePlan excursionGuidePlan;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DateTimePath<java.util.Date> occupiedDate = createDateTime("occupiedDate", java.util.Date.class);

    public QExcursionGuideOccupied(String variable) {
        this(ExcursionGuideOccupied.class, forVariable(variable), INITS);
    }

    public QExcursionGuideOccupied(Path<? extends ExcursionGuideOccupied> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QExcursionGuideOccupied(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QExcursionGuideOccupied(PathMetadata<?> metadata, PathInits inits) {
        this(ExcursionGuideOccupied.class, metadata, inits);
    }

    public QExcursionGuideOccupied(Class<? extends ExcursionGuideOccupied> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.excursionGuide = inits.isInitialized("excursionGuide") ? new QExcursionGuide(forProperty("excursionGuide"), inits.get("excursionGuide")) : null;
        this.excursionGuidePlan = inits.isInitialized("excursionGuidePlan") ? new QExcursionGuidePlan(forProperty("excursionGuidePlan"), inits.get("excursionGuidePlan")) : null;
    }

}

