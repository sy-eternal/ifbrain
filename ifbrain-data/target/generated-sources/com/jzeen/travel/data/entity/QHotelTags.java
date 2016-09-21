package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QHotelTags is a Querydsl query type for HotelTags
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QHotelTags extends EntityPathBase<HotelTags> {

    private static final long serialVersionUID = 344746095L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHotelTags hotelTags = new QHotelTags("hotelTags");

    public final DateTimePath<java.util.Date> createtime = createDateTime("createtime", java.util.Date.class);

    public final QHotelActivity hotelActivity;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath tag = createString("tag");

    public QHotelTags(String variable) {
        this(HotelTags.class, forVariable(variable), INITS);
    }

    public QHotelTags(Path<? extends HotelTags> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QHotelTags(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QHotelTags(PathMetadata<?> metadata, PathInits inits) {
        this(HotelTags.class, metadata, inits);
    }

    public QHotelTags(Class<? extends HotelTags> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hotelActivity = inits.isInitialized("hotelActivity") ? new QHotelActivity(forProperty("hotelActivity"), inits.get("hotelActivity")) : null;
    }

}

