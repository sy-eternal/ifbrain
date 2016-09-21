package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QTripBlogComments is a Querydsl query type for TripBlogComments
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTripBlogComments extends EntityPathBase<TripBlogComments> {

    private static final long serialVersionUID = -361875015L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTripBlogComments tripBlogComments = new QTripBlogComments("tripBlogComments");

    public final StringPath comment = createString("comment");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QTripBlog tripBlog;

    public final QUser user;

    public QTripBlogComments(String variable) {
        this(TripBlogComments.class, forVariable(variable), INITS);
    }

    public QTripBlogComments(Path<? extends TripBlogComments> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTripBlogComments(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTripBlogComments(PathMetadata<?> metadata, PathInits inits) {
        this(TripBlogComments.class, metadata, inits);
    }

    public QTripBlogComments(Class<? extends TripBlogComments> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.tripBlog = inits.isInitialized("tripBlog") ? new QTripBlog(forProperty("tripBlog"), inits.get("tripBlog")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

