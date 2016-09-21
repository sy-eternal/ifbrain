package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QStandardGuideOccupied is a Querydsl query type for StandardGuideOccupied
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QStandardGuideOccupied extends EntityPathBase<StandardGuideOccupied> {

    private static final long serialVersionUID = -175294593L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStandardGuideOccupied standardGuideOccupied = new QStandardGuideOccupied("standardGuideOccupied");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DateTimePath<java.util.Date> occupiedDate = createDateTime("occupiedDate", java.util.Date.class);

    public final NumberPath<Integer> occupiedType = createNumber("occupiedType", Integer.class);

    public final BooleanPath otStatus = createBoolean("otStatus");

    public final NumberPath<Integer> otTime = createNumber("otTime", Integer.class);

    public final QStandardGuide standardGuide;

    public final QStandardGuidePlan standardGuidePlan;

    public QStandardGuideOccupied(String variable) {
        this(StandardGuideOccupied.class, forVariable(variable), INITS);
    }

    public QStandardGuideOccupied(Path<? extends StandardGuideOccupied> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStandardGuideOccupied(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStandardGuideOccupied(PathMetadata<?> metadata, PathInits inits) {
        this(StandardGuideOccupied.class, metadata, inits);
    }

    public QStandardGuideOccupied(Class<? extends StandardGuideOccupied> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.standardGuide = inits.isInitialized("standardGuide") ? new QStandardGuide(forProperty("standardGuide"), inits.get("standardGuide")) : null;
        this.standardGuidePlan = inits.isInitialized("standardGuidePlan") ? new QStandardGuidePlan(forProperty("standardGuidePlan"), inits.get("standardGuidePlan")) : null;
    }

}

