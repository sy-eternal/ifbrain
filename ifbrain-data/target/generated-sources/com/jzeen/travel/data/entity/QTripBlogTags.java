package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QTripBlogTags is a Querydsl query type for TripBlogTags
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTripBlogTags extends EntityPathBase<TripBlogTags> {

    private static final long serialVersionUID = 1018378846L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTripBlogTags tripBlogTags = new QTripBlogTags("tripBlogTags");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath tag = createString("tag");

    public final QTripBlog tripBlog;

    public QTripBlogTags(String variable) {
        this(TripBlogTags.class, forVariable(variable), INITS);
    }

    public QTripBlogTags(Path<? extends TripBlogTags> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTripBlogTags(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTripBlogTags(PathMetadata<?> metadata, PathInits inits) {
        this(TripBlogTags.class, metadata, inits);
    }

    public QTripBlogTags(Class<? extends TripBlogTags> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.tripBlog = inits.isInitialized("tripBlog") ? new QTripBlog(forProperty("tripBlog"), inits.get("tripBlog")) : null;
    }

}

