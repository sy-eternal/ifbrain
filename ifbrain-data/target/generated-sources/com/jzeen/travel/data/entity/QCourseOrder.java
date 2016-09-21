package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCourseOrder is a Querydsl query type for CourseOrder
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCourseOrder extends EntityPathBase<CourseOrder> {

    private static final long serialVersionUID = -1549438987L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCourseOrder courseOrder = new QCourseOrder("courseOrder");

    public final StringPath courseLevel = createString("courseLevel");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<java.math.BigDecimal> netPrice = createNumber("netPrice", java.math.BigDecimal.class);

    public final StringPath openid = createString("openid");

    public final StringPath orderNumber = createString("orderNumber");

    public final NumberPath<Integer> payStatus = createNumber("payStatus", Integer.class);

    public final DateTimePath<java.util.Date> payTime = createDateTime("payTime", java.util.Date.class);

    public final StringPath payWechatid = createString("payWechatid");

    public final NumberPath<java.math.BigDecimal> price = createNumber("price", java.math.BigDecimal.class);

    public final QChild tCId;

    public final QCourse tCId2;

    public final NumberPath<Integer> userId = createNumber("userId", Integer.class);

    public QCourseOrder(String variable) {
        this(CourseOrder.class, forVariable(variable), INITS);
    }

    public QCourseOrder(Path<? extends CourseOrder> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCourseOrder(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCourseOrder(PathMetadata<?> metadata, PathInits inits) {
        this(CourseOrder.class, metadata, inits);
    }

    public QCourseOrder(Class<? extends CourseOrder> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.tCId = inits.isInitialized("tCId") ? new QChild(forProperty("tCId"), inits.get("tCId")) : null;
        this.tCId2 = inits.isInitialized("tCId2") ? new QCourse(forProperty("tCId2")) : null;
    }

}

