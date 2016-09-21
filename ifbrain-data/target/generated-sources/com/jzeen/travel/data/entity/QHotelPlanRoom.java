package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QHotelPlanRoom is a Querydsl query type for HotelPlanRoom
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QHotelPlanRoom extends EntityPathBase<HotelPlanRoom> {

    private static final long serialVersionUID = 1245175034L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHotelPlanRoom hotelPlanRoom = new QHotelPlanRoom("hotelPlanRoom");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final QHotelPlan hotelPlan;

    public final QHotelRoomType hotelRoomType;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> roomCount = createNumber("roomCount", Integer.class);

    public final NumberPath<java.math.BigDecimal> salePrice = createNumber("salePrice", java.math.BigDecimal.class);

    public final StringPath supplierNum = createString("supplierNum");

    public QHotelPlanRoom(String variable) {
        this(HotelPlanRoom.class, forVariable(variable), INITS);
    }

    public QHotelPlanRoom(Path<? extends HotelPlanRoom> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QHotelPlanRoom(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QHotelPlanRoom(PathMetadata<?> metadata, PathInits inits) {
        this(HotelPlanRoom.class, metadata, inits);
    }

    public QHotelPlanRoom(Class<? extends HotelPlanRoom> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hotelPlan = inits.isInitialized("hotelPlan") ? new QHotelPlan(forProperty("hotelPlan"), inits.get("hotelPlan")) : null;
        this.hotelRoomType = inits.isInitialized("hotelRoomType") ? new QHotelRoomType(forProperty("hotelRoomType"), inits.get("hotelRoomType")) : null;
    }

}

