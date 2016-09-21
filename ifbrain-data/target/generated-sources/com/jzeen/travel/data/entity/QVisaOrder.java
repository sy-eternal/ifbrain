package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QVisaOrder is a Querydsl query type for VisaOrder
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QVisaOrder extends EntityPathBase<VisaOrder> {

    private static final long serialVersionUID = -2084645873L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVisaOrder visaOrder = new QVisaOrder("visaOrder");

    public final StringPath comment = createString("comment");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final StringPath expressName = createString("expressName");

    public final StringPath expressNumber = createString("expressNumber");

    public final NumberPath<Integer> headCount = createNumber("headCount", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DateTimePath<java.util.Date> interviewDate = createDateTime("interviewDate", java.util.Date.class);

    public final StringPath interviewMemo = createString("interviewMemo");

    public final StringPath interviewPlace = createString("interviewPlace");

    public final StringPath interviewTime = createString("interviewTime");

    public final StringPath orderNumber = createString("orderNumber");

    public final NumberPath<Integer> orderStatus = createNumber("orderStatus", Integer.class);

    public final StringPath paymentId = createString("paymentId");

    public final StringPath postAddress = createString("postAddress");

    public final StringPath receiverName = createString("receiverName");

    public final StringPath taobaoOrderNumber = createString("taobaoOrderNumber");

    public final StringPath teleNumber = createString("teleNumber");

    public final NumberPath<java.math.BigDecimal> totalAmount = createNumber("totalAmount", java.math.BigDecimal.class);

    public final QUser user;

    public final StringPath wechatId = createString("wechatId");

    public QVisaOrder(String variable) {
        this(VisaOrder.class, forVariable(variable), INITS);
    }

    public QVisaOrder(Path<? extends VisaOrder> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVisaOrder(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVisaOrder(PathMetadata<?> metadata, PathInits inits) {
        this(VisaOrder.class, metadata, inits);
    }

    public QVisaOrder(Class<? extends VisaOrder> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

