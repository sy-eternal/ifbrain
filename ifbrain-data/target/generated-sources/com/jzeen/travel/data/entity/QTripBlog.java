package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QTripBlog is a Querydsl query type for TripBlog
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTripBlog extends EntityPathBase<TripBlog> {

    private static final long serialVersionUID = -410227547L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTripBlog tripBlog = new QTripBlog("tripBlog");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final StringPath description = createString("description");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QImage image;

    public final StringPath tile = createString("tile");

    public final DateTimePath<java.util.Date> time = createDateTime("time", java.util.Date.class);

    public final ListPath<TripBlogComments, QTripBlogComments> tripBlogComments = this.<TripBlogComments, QTripBlogComments>createList("tripBlogComments", TripBlogComments.class, QTripBlogComments.class, PathInits.DIRECT2);

    public final ListPath<TripBlogItem, QTripBlogItem> tripblogItem = this.<TripBlogItem, QTripBlogItem>createList("tripblogItem", TripBlogItem.class, QTripBlogItem.class, PathInits.DIRECT2);

    public final ListPath<TripBlogTags, QTripBlogTags> tripBlogTags = this.<TripBlogTags, QTripBlogTags>createList("tripBlogTags", TripBlogTags.class, QTripBlogTags.class, PathInits.DIRECT2);

    public final QUser user;

    public QTripBlog(String variable) {
        this(TripBlog.class, forVariable(variable), INITS);
    }

    public QTripBlog(Path<? extends TripBlog> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTripBlog(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTripBlog(PathMetadata<?> metadata, PathInits inits) {
        this(TripBlog.class, metadata, inits);
    }

    public QTripBlog(Class<? extends TripBlog> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.image = inits.isInitialized("image") ? new QImage(forProperty("image"), inits.get("image")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

