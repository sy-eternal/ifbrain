package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QInsurancePlan is a Querydsl query type for InsurancePlan
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QInsurancePlan extends EntityPathBase<InsurancePlan> {

    private static final long serialVersionUID = -1392471899L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInsurancePlan insurancePlan = new QInsurancePlan("insurancePlan");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final StringPath holderType = createString("holderType");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QInsuranceActivity insuranceActivity;

    public final StringPath insuranceDuration = createString("insuranceDuration");

    public final StringPath insuranceName = createString("insuranceName");

    public final QOrder order;

    public final NumberPath<Integer> personCount = createNumber("personCount", Integer.class);

    public final NumberPath<java.math.BigDecimal> subTotalAmount = createNumber("subTotalAmount", java.math.BigDecimal.class);

    public QInsurancePlan(String variable) {
        this(InsurancePlan.class, forVariable(variable), INITS);
    }

    public QInsurancePlan(Path<? extends InsurancePlan> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QInsurancePlan(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QInsurancePlan(PathMetadata<?> metadata, PathInits inits) {
        this(InsurancePlan.class, metadata, inits);
    }

    public QInsurancePlan(Class<? extends InsurancePlan> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.insuranceActivity = inits.isInitialized("insuranceActivity") ? new QInsuranceActivity(forProperty("insuranceActivity"), inits.get("insuranceActivity")) : null;
        this.order = inits.isInitialized("order") ? new QOrder(forProperty("order"), inits.get("order")) : null;
    }

}

