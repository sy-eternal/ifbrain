package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QHotelActivity is a Querydsl query type for HotelActivity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QHotelActivity extends EntityPathBase<HotelActivity> {

    private static final long serialVersionUID = 2015628389L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHotelActivity hotelActivity = new QHotelActivity("hotelActivity");

    public final StringPath address = createString("address");

    public final NumberPath<Integer> airportShuttleType = createNumber("airportShuttleType", Integer.class);

    public final QCity city;

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final StringPath description = createString("description");

    public final NumberPath<Integer> fitnessCenterType = createNumber("fitnessCenterType", Integer.class);

    public final NumberPath<Integer> freeBreakfastType = createNumber("freeBreakfastType", Integer.class);

    public final NumberPath<Integer> freeCancellation = createNumber("freeCancellation", Integer.class);

    public final NumberPath<Integer> freeInternetType = createNumber("freeInternetType", Integer.class);

    public final NumberPath<Integer> freeParkingType = createNumber("freeParkingType", Integer.class);

    public final StringPath hotelChName = createString("hotelChName");

    public final NumberPath<Integer> hotelClass = createNumber("hotelClass", Integer.class);

    public final StringPath hotelEngName = createString("hotelEngName");

    public final ListPath<HotelRoomType, QHotelRoomType> hotelRoomType = this.<HotelRoomType, QHotelRoomType>createList("hotelRoomType", HotelRoomType.class, QHotelRoomType.class, PathInits.DIRECT2);

    public final ListPath<HotelTags, QHotelTags> hotelTags = this.<HotelTags, QHotelTags>createList("hotelTags", HotelTags.class, QHotelTags.class, PathInits.DIRECT2);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QImage image;

    public final QSupplier supplier;

    public QHotelActivity(String variable) {
        this(HotelActivity.class, forVariable(variable), INITS);
    }

    public QHotelActivity(Path<? extends HotelActivity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QHotelActivity(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QHotelActivity(PathMetadata<?> metadata, PathInits inits) {
        this(HotelActivity.class, metadata, inits);
    }

    public QHotelActivity(Class<? extends HotelActivity> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.city = inits.isInitialized("city") ? new QCity(forProperty("city"), inits.get("city")) : null;
        this.image = inits.isInitialized("image") ? new QImage(forProperty("image"), inits.get("image")) : null;
        this.supplier = inits.isInitialized("supplier") ? new QSupplier(forProperty("supplier")) : null;
    }

}

