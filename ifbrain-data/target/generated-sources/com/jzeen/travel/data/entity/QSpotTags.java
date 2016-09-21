package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSpotTags is a Querydsl query type for SpotTags
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSpotTags extends EntityPathBase<SpotTags> {

    private static final long serialVersionUID = 542916473L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSpotTags spotTags = new QSpotTags("spotTags");

    public final DateTimePath<java.util.Date> createtime = createDateTime("createtime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QSpot spot;

    public final StringPath tag = createString("tag");

    public QSpotTags(String variable) {
        this(SpotTags.class, forVariable(variable), INITS);
    }

    public QSpotTags(Path<? extends SpotTags> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSpotTags(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSpotTags(PathMetadata<?> metadata, PathInits inits) {
        this(SpotTags.class, metadata, inits);
    }

    public QSpotTags(Class<? extends SpotTags> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.spot = inits.isInitialized("spot") ? new QSpot(forProperty("spot"), inits.get("spot")) : null;
    }

}

