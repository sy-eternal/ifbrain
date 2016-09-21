package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSpotThemeRelate is a Querydsl query type for SpotThemeRelate
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSpotThemeRelate extends EntityPathBase<SpotThemeRelate> {

    private static final long serialVersionUID = 173268770L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSpotThemeRelate spotThemeRelate = new QSpotThemeRelate("spotThemeRelate");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QSpot spot;

    public final QThemeActive theme;

    public QSpotThemeRelate(String variable) {
        this(SpotThemeRelate.class, forVariable(variable), INITS);
    }

    public QSpotThemeRelate(Path<? extends SpotThemeRelate> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSpotThemeRelate(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSpotThemeRelate(PathMetadata<?> metadata, PathInits inits) {
        this(SpotThemeRelate.class, metadata, inits);
    }

    public QSpotThemeRelate(Class<? extends SpotThemeRelate> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.spot = inits.isInitialized("spot") ? new QSpot(forProperty("spot"), inits.get("spot")) : null;
        this.theme = inits.isInitialized("theme") ? new QThemeActive(forProperty("theme"), inits.get("theme")) : null;
    }

}

