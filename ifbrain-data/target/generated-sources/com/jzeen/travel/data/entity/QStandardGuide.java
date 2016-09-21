package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QStandardGuide is a Querydsl query type for StandardGuide
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QStandardGuide extends EntityPathBase<StandardGuide> {

    private static final long serialVersionUID = -1104428607L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStandardGuide standardGuide = new QStandardGuide("standardGuide");

    public final StringPath accountName = createString("accountName");

    public final StringPath accountNum = createString("accountNum");

    public final StringPath bankAddress = createString("bankAddress");

    public final StringPath bankName = createString("bankName");

    public final StringPath bankTel = createString("bankTel");

    public final NumberPath<java.math.BigDecimal> commisionCost = createNumber("commisionCost", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> commisionPercentage = createNumber("commisionPercentage", java.math.BigDecimal.class);

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final StringPath domesticRoutingNum = createString("domesticRoutingNum");

    public final NumberPath<java.math.BigDecimal> excursionGuideCost = createNumber("excursionGuideCost", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> excursionGuidePrice = createNumber("excursionGuidePrice", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> guideCost = createNumber("guideCost", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> guidePrice = createNumber("guidePrice", java.math.BigDecimal.class);

    public final BooleanPath hasCar = createBoolean("hasCar");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath internationalSwiftNum = createString("internationalSwiftNum");

    public final NumberPath<java.math.BigDecimal> otCost = createNumber("otCost", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> otPrice = createNumber("otPrice", java.math.BigDecimal.class);

    public final NumberPath<Integer> otStatus = createNumber("otStatus", Integer.class);

    public final DateTimePath<java.util.Date> otTime = createDateTime("otTime", java.util.Date.class);

    public final NumberPath<java.math.BigDecimal> rate = createNumber("rate", java.math.BigDecimal.class);

    public final ListPath<StandardGuideOccupied, QStandardGuideOccupied> standardGuideOccupied = this.<StandardGuideOccupied, QStandardGuideOccupied>createList("standardGuideOccupied", StandardGuideOccupied.class, QStandardGuideOccupied.class, PathInits.DIRECT2);

    public final QGuide user;

    public QStandardGuide(String variable) {
        this(StandardGuide.class, forVariable(variable), INITS);
    }

    public QStandardGuide(Path<? extends StandardGuide> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStandardGuide(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStandardGuide(PathMetadata<?> metadata, PathInits inits) {
        this(StandardGuide.class, metadata, inits);
    }

    public QStandardGuide(Class<? extends StandardGuide> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QGuide(forProperty("user"), inits.get("user")) : null;
    }

}

