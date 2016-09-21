package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QGuideOrder is a Querydsl query type for GuideOrder
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QGuideOrder extends EntityPathBase<GuideOrder> {

    private static final long serialVersionUID = 1250816368L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGuideOrder guideOrder = new QGuideOrder("guideOrder");

    public final NumberPath<Integer> appointedstatus = createNumber("appointedstatus", Integer.class);

    public final QCity city;

    public final DateTimePath<java.util.Date> createtime = createDateTime("createtime", java.util.Date.class);

    public final NumberPath<Integer> duration = createNumber("duration", Integer.class);

    public final NumberPath<Integer> guidecount = createNumber("guidecount", Integer.class);

    public final StringPath guideordernum = createString("guideordernum");

    public final NumberPath<java.math.BigDecimal> guideserviceammount = createNumber("guideserviceammount", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> guidetipsammount = createNumber("guidetipsammount", java.math.BigDecimal.class);

    public final StringPath guidetype = createString("guidetype");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<java.math.BigDecimal> orderamount = createNumber("orderamount", java.math.BigDecimal.class);

    public final NumberPath<Integer> orderstatus = createNumber("orderstatus", Integer.class);

    public final NumberPath<Integer> personcount = createNumber("personcount", Integer.class);

    public final DateTimePath<java.util.Date> startdate = createDateTime("startdate", java.util.Date.class);

    public final QUser user;

    public QGuideOrder(String variable) {
        this(GuideOrder.class, forVariable(variable), INITS);
    }

    public QGuideOrder(Path<? extends GuideOrder> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QGuideOrder(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QGuideOrder(PathMetadata<?> metadata, PathInits inits) {
        this(GuideOrder.class, metadata, inits);
    }

    public QGuideOrder(Class<? extends GuideOrder> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.city = inits.isInitialized("city") ? new QCity(forProperty("city"), inits.get("city")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

