package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QTripBlogItemImageRelate is a Querydsl query type for TripBlogItemImageRelate
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTripBlogItemImageRelate extends EntityPathBase<TripBlogItemImageRelate> {

    private static final long serialVersionUID = -1233152996L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTripBlogItemImageRelate tripBlogItemImageRelate = new QTripBlogItemImageRelate("tripBlogItemImageRelate");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QImage image;

    public final QTripBlogItem tripblogItem;

    public QTripBlogItemImageRelate(String variable) {
        this(TripBlogItemImageRelate.class, forVariable(variable), INITS);
    }

    public QTripBlogItemImageRelate(Path<? extends TripBlogItemImageRelate> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTripBlogItemImageRelate(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTripBlogItemImageRelate(PathMetadata<?> metadata, PathInits inits) {
        this(TripBlogItemImageRelate.class, metadata, inits);
    }

    public QTripBlogItemImageRelate(Class<? extends TripBlogItemImageRelate> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.image = inits.isInitialized("image") ? new QImage(forProperty("image"), inits.get("image")) : null;
        this.tripblogItem = inits.isInitialized("tripblogItem") ? new QTripBlogItem(forProperty("tripblogItem"), inits.get("tripblogItem")) : null;
    }

}

