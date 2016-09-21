package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QGuideInstandingOrders is a Querydsl query type for GuideInstandingOrders
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QGuideInstandingOrders extends EntityPathBase<GuideInstandingOrders> {

    private static final long serialVersionUID = -680585388L;

    public static final QGuideInstandingOrders guideInstandingOrders = new QGuideInstandingOrders("guideInstandingOrders");

    public final DateTimePath<java.util.Date> endDate = createDateTime("endDate", java.util.Date.class);

    public final StringPath firstName = createString("firstName");

    public final NumberPath<Integer> guidePk = createNumber("guidePk", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath lastName = createString("lastName");

    public final StringPath orderNumber = createString("orderNumber");

    public final NumberPath<Integer> orderStatus = createNumber("orderStatus", Integer.class);

    public final DateTimePath<java.util.Date> startDate = createDateTime("startDate", java.util.Date.class);

    public QGuideInstandingOrders(String variable) {
        super(GuideInstandingOrders.class, forVariable(variable));
    }

    public QGuideInstandingOrders(Path<? extends GuideInstandingOrders> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGuideInstandingOrders(PathMetadata<?> metadata) {
        super(GuideInstandingOrders.class, metadata);
    }

}

