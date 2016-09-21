package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QTripBlogItem is a Querydsl query type for TripBlogItem
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTripBlogItem extends EntityPathBase<TripBlogItem> {

    private static final long serialVersionUID = 1018069336L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTripBlogItem tripBlogItem = new QTripBlogItem("tripBlogItem");

    public final StringPath ch_name = createString("ch_name");

    public final QCity city;

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final StringPath description = createString("description");

    public final StringPath eng_name = createString("eng_name");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath tips = createString("tips");

    public final QTripBlog tripBlog;

    public final ListPath<TripBlogItemImageRelate, QTripBlogItemImageRelate> tripBlogItemImageRelate = this.<TripBlogItemImageRelate, QTripBlogItemImageRelate>createList("tripBlogItemImageRelate", TripBlogItemImageRelate.class, QTripBlogItemImageRelate.class, PathInits.DIRECT2);

    public QTripBlogItem(String variable) {
        this(TripBlogItem.class, forVariable(variable), INITS);
    }

    public QTripBlogItem(Path<? extends TripBlogItem> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTripBlogItem(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTripBlogItem(PathMetadata<?> metadata, PathInits inits) {
        this(TripBlogItem.class, metadata, inits);
    }

    public QTripBlogItem(Class<? extends TripBlogItem> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.city = inits.isInitialized("city") ? new QCity(forProperty("city"), inits.get("city")) : null;
        this.tripBlog = inits.isInitialized("tripBlog") ? new QTripBlog(forProperty("tripBlog"), inits.get("tripBlog")) : null;
    }

}

