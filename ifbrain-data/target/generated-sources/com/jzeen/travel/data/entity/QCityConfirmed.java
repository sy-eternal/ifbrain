package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCityConfirmed is a Querydsl query type for CityConfirmed
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCityConfirmed extends EntityPathBase<CityConfirmed> {

    private static final long serialVersionUID = -794355274L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCityConfirmed cityConfirmed = new QCityConfirmed("cityConfirmed");

    public final NumberPath<Integer> arrangedStatus = createNumber("arrangedStatus", Integer.class);

    public final QCity city;

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final DateTimePath<java.util.Date> endDate = createDateTime("endDate", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QOrder order;

    public final StringPath remark = createString("remark");

    public final DateTimePath<java.util.Date> startDate = createDateTime("startDate", java.util.Date.class);

    public QCityConfirmed(String variable) {
        this(CityConfirmed.class, forVariable(variable), INITS);
    }

    public QCityConfirmed(Path<? extends CityConfirmed> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCityConfirmed(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCityConfirmed(PathMetadata<?> metadata, PathInits inits) {
        this(CityConfirmed.class, metadata, inits);
    }

    public QCityConfirmed(Class<? extends CityConfirmed> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.city = inits.isInitialized("city") ? new QCity(forProperty("city"), inits.get("city")) : null;
        this.order = inits.isInitialized("order") ? new QOrder(forProperty("order"), inits.get("order")) : null;
    }

}

