package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QExamOrder is a Querydsl query type for ExamOrder
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QExamOrder extends EntityPathBase<ExamOrder> {

    private static final long serialVersionUID = 498012913L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QExamOrder examOrder = new QExamOrder("examOrder");

    public final QChild child;

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<java.math.BigDecimal> orderAmount = createNumber("orderAmount", java.math.BigDecimal.class);

    public final StringPath orderNumber = createString("orderNumber");

    public final NumberPath<Integer> orderStatus = createNumber("orderStatus", Integer.class);

    public final QUser user;

    public QExamOrder(String variable) {
        this(ExamOrder.class, forVariable(variable), INITS);
    }

    public QExamOrder(Path<? extends ExamOrder> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QExamOrder(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QExamOrder(PathMetadata<?> metadata, PathInits inits) {
        this(ExamOrder.class, metadata, inits);
    }

    public QExamOrder(Class<? extends ExamOrder> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.child = inits.isInitialized("child") ? new QChild(forProperty("child"), inits.get("child")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

