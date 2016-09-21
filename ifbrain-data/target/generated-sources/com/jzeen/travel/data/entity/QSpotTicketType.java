package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSpotTicketType is a Querydsl query type for SpotTicketType
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSpotTicketType extends EntityPathBase<SpotTicketType> {

    private static final long serialVersionUID = 588516294L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSpotTicketType spotTicketType = new QSpotTicketType("spotTicketType");

    public final NumberPath<java.math.BigDecimal> cost = createNumber("cost", java.math.BigDecimal.class);

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath includingItem = createString("includingItem");

    public final NumberPath<java.math.BigDecimal> price = createNumber("price", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> pricerate = createNumber("pricerate", java.math.BigDecimal.class);

    public final QSpot spots;

    public final StringPath type = createString("type");

    public QSpotTicketType(String variable) {
        this(SpotTicketType.class, forVariable(variable), INITS);
    }

    public QSpotTicketType(Path<? extends SpotTicketType> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSpotTicketType(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSpotTicketType(PathMetadata<?> metadata, PathInits inits) {
        this(SpotTicketType.class, metadata, inits);
    }

    public QSpotTicketType(Class<? extends SpotTicketType> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.spots = inits.isInitialized("spots") ? new QSpot(forProperty("spots"), inits.get("spots")) : null;
    }

}

