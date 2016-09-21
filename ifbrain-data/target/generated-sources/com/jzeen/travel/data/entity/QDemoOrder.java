package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QDemoOrder is a Querydsl query type for DemoOrder
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDemoOrder extends EntityPathBase<DemoOrder> {

    private static final long serialVersionUID = 1374209965L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDemoOrder demoOrder = new QDemoOrder("demoOrder");

    public final StringPath address = createString("address");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final QDemoUser demoUser;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath mobile = createString("mobile");

    public final StringPath remark = createString("remark");

    public final StringPath reveiverName = createString("reveiverName");

    public QDemoOrder(String variable) {
        this(DemoOrder.class, forVariable(variable), INITS);
    }

    public QDemoOrder(Path<? extends DemoOrder> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDemoOrder(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDemoOrder(PathMetadata<?> metadata, PathInits inits) {
        this(DemoOrder.class, metadata, inits);
    }

    public QDemoOrder(Class<? extends DemoOrder> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.demoUser = inits.isInitialized("demoUser") ? new QDemoUser(forProperty("demoUser")) : null;
    }

}

