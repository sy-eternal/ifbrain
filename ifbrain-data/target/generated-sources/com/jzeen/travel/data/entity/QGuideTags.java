package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QGuideTags is a Querydsl query type for GuideTags
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QGuideTags extends EntityPathBase<GuideTags> {

    private static final long serialVersionUID = -1483539017L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGuideTags guideTags = new QGuideTags("guideTags");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final QGuide guide;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath tag = createString("tag");

    public QGuideTags(String variable) {
        this(GuideTags.class, forVariable(variable), INITS);
    }

    public QGuideTags(Path<? extends GuideTags> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QGuideTags(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QGuideTags(PathMetadata<?> metadata, PathInits inits) {
        this(GuideTags.class, metadata, inits);
    }

    public QGuideTags(Class<? extends GuideTags> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.guide = inits.isInitialized("guide") ? new QGuide(forProperty("guide"), inits.get("guide")) : null;
    }

}

