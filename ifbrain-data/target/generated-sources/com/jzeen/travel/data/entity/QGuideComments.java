package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QGuideComments is a Querydsl query type for GuideComments
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QGuideComments extends EntityPathBase<GuideComments> {

    private static final long serialVersionUID = -1902466926L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGuideComments guideComments = new QGuideComments("guideComments");

    public final StringPath comments = createString("comments");

    public final DateTimePath<java.util.Date> createtime = createDateTime("createtime", java.util.Date.class);

    public final QGuide guide;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QUser user;

    public QGuideComments(String variable) {
        this(GuideComments.class, forVariable(variable), INITS);
    }

    public QGuideComments(Path<? extends GuideComments> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QGuideComments(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QGuideComments(PathMetadata<?> metadata, PathInits inits) {
        this(GuideComments.class, metadata, inits);
    }

    public QGuideComments(Class<? extends GuideComments> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.guide = inits.isInitialized("guide") ? new QGuide(forProperty("guide"), inits.get("guide")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

