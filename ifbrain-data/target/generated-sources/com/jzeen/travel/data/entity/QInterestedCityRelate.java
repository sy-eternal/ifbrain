package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QInterestedCityRelate is a Querydsl query type for InterestedCityRelate
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QInterestedCityRelate extends EntityPathBase<InterestedCityRelate> {

    private static final long serialVersionUID = -674234101L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInterestedCityRelate interestedCityRelate = new QInterestedCityRelate("interestedCityRelate");

    public final QCity city;

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QOrder order;

    public QInterestedCityRelate(String variable) {
        this(InterestedCityRelate.class, forVariable(variable), INITS);
    }

    public QInterestedCityRelate(Path<? extends InterestedCityRelate> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QInterestedCityRelate(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QInterestedCityRelate(PathMetadata<?> metadata, PathInits inits) {
        this(InterestedCityRelate.class, metadata, inits);
    }

    public QInterestedCityRelate(Class<? extends InterestedCityRelate> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.city = inits.isInitialized("city") ? new QCity(forProperty("city"), inits.get("city")) : null;
        this.order = inits.isInitialized("order") ? new QOrder(forProperty("order"), inits.get("order")) : null;
    }

}

