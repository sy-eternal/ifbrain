package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QGuideOutstandingOrders is a Querydsl query type for GuideOutstandingOrders
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QGuideOutstandingOrders extends EntityPathBase<GuideOutstandingOrders> {

    private static final long serialVersionUID = -1525663679L;

    public static final QGuideOutstandingOrders guideOutstandingOrders = new QGuideOutstandingOrders("guideOutstandingOrders");

    public final DateTimePath<java.util.Date> endDate = createDateTime("endDate", java.util.Date.class);

    public final StringPath firstName = createString("firstName");

    public final NumberPath<Integer> guidePk = createNumber("guidePk", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath lastName = createString("lastName");

    public final StringPath orderNumber = createString("orderNumber");

    public final NumberPath<Integer> orderStatus = createNumber("orderStatus", Integer.class);

    public final DateTimePath<java.util.Date> startDate = createDateTime("startDate", java.util.Date.class);

    public QGuideOutstandingOrders(String variable) {
        super(GuideOutstandingOrders.class, forVariable(variable));
    }

    public QGuideOutstandingOrders(Path<? extends GuideOutstandingOrders> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGuideOutstandingOrders(PathMetadata<?> metadata) {
        super(GuideOutstandingOrders.class, metadata);
    }

}

