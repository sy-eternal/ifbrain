package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QInnerCityTrafficRelate is a Querydsl query type for InnerCityTrafficRelate
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QInnerCityTrafficRelate extends EntityPathBase<InnerCityTrafficRelate> {

    private static final long serialVersionUID = 294566675L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInnerCityTrafficRelate innerCityTrafficRelate = new QInnerCityTrafficRelate("innerCityTrafficRelate");

    public final NumberPath<Integer> completeItenary = createNumber("completeItenary", Integer.class);

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath innerCityTraffic = createString("innerCityTraffic");

    public final QOrder order;

    public final NumberPath<Integer> orderPk = createNumber("orderPk", Integer.class);

    public final StringPath remark = createString("remark");

    public QInnerCityTrafficRelate(String variable) {
        this(InnerCityTrafficRelate.class, forVariable(variable), INITS);
    }

    public QInnerCityTrafficRelate(Path<? extends InnerCityTrafficRelate> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QInnerCityTrafficRelate(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QInnerCityTrafficRelate(PathMetadata<?> metadata, PathInits inits) {
        this(InnerCityTrafficRelate.class, metadata, inits);
    }

    public QInnerCityTrafficRelate(Class<? extends InnerCityTrafficRelate> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.order = inits.isInitialized("order") ? new QOrder(forProperty("order"), inits.get("order")) : null;
    }

}

