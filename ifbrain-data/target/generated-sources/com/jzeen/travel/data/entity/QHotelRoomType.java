package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QHotelRoomType is a Querydsl query type for HotelRoomType
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QHotelRoomType extends EntityPathBase<HotelRoomType> {

    private static final long serialVersionUID = -796674037L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHotelRoomType hotelRoomType = new QHotelRoomType("hotelRoomType");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final StringPath description = createString("description");

    public final QHotelActivity hotelActivity;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<java.math.BigDecimal> perNightCost = createNumber("perNightCost", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> perNightPrice = createNumber("perNightPrice", java.math.BigDecimal.class);

    public final StringPath promotionContent = createString("promotionContent");

    public final StringPath roomType = createString("roomType");

    public QHotelRoomType(String variable) {
        this(HotelRoomType.class, forVariable(variable), INITS);
    }

    public QHotelRoomType(Path<? extends HotelRoomType> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QHotelRoomType(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QHotelRoomType(PathMetadata<?> metadata, PathInits inits) {
        this(HotelRoomType.class, metadata, inits);
    }

    public QHotelRoomType(Class<? extends HotelRoomType> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hotelActivity = inits.isInitialized("hotelActivity") ? new QHotelActivity(forProperty("hotelActivity"), inits.get("hotelActivity")) : null;
    }

}

