package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QZDemoOrder is a Querydsl query type for ZDemoOrder
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QZDemoOrder extends EntityPathBase<ZDemoOrder> {

    private static final long serialVersionUID = 1485565839L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QZDemoOrder zDemoOrder = new QZDemoOrder("zDemoOrder");

    public final StringPath address = createString("address");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath mobile = createString("mobile");

    public final StringPath remark = createString("remark");

    public final StringPath reveiverName = createString("reveiverName");

    public final QZDemoUser zDemoUser;

    public QZDemoOrder(String variable) {
        this(ZDemoOrder.class, forVariable(variable), INITS);
    }

    public QZDemoOrder(Path<? extends ZDemoOrder> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QZDemoOrder(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QZDemoOrder(PathMetadata<?> metadata, PathInits inits) {
        this(ZDemoOrder.class, metadata, inits);
    }

    public QZDemoOrder(Class<? extends ZDemoOrder> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.zDemoUser = inits.isInitialized("zDemoUser") ? new QZDemoUser(forProperty("zDemoUser")) : null;
    }

}

