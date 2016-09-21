package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QExcursionGuide is a Querydsl query type for ExcursionGuide
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QExcursionGuide extends EntityPathBase<ExcursionGuide> {

    private static final long serialVersionUID = 817101816L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QExcursionGuide excursionGuide = new QExcursionGuide("excursionGuide");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<java.math.BigDecimal> excursionGuideCost = createNumber("excursionGuideCost", java.math.BigDecimal.class);

    public final ListPath<ExcursionGuideOccupied, QExcursionGuideOccupied> excursionGuideOccupieds = this.<ExcursionGuideOccupied, QExcursionGuideOccupied>createList("excursionGuideOccupieds", ExcursionGuideOccupied.class, QExcursionGuideOccupied.class, PathInits.DIRECT2);

    public final NumberPath<java.math.BigDecimal> excursionGuidePrice = createNumber("excursionGuidePrice", java.math.BigDecimal.class);

    public final QGuide guide;

    public final BooleanPath hasCar = createBoolean("hasCar");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QExcursionGuide(String variable) {
        this(ExcursionGuide.class, forVariable(variable), INITS);
    }

    public QExcursionGuide(Path<? extends ExcursionGuide> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QExcursionGuide(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QExcursionGuide(PathMetadata<?> metadata, PathInits inits) {
        this(ExcursionGuide.class, metadata, inits);
    }

    public QExcursionGuide(Class<? extends ExcursionGuide> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.guide = inits.isInitialized("guide") ? new QGuide(forProperty("guide"), inits.get("guide")) : null;
    }

}

