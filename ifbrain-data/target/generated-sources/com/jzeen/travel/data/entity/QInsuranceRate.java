package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QInsuranceRate is a Querydsl query type for InsuranceRate
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QInsuranceRate extends EntityPathBase<InsuranceRate> {

    private static final long serialVersionUID = -1392422308L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInsuranceRate insuranceRate = new QInsuranceRate("insuranceRate");

    public final StringPath holderType = createString("holderType");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QInsuranceActivity insuranceActivity;

    public final StringPath insuranceDuration = createString("insuranceDuration");

    public final NumberPath<java.math.BigDecimal> insuranceRateCost = createNumber("insuranceRateCost", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> insuranceRatePrice = createNumber("insuranceRatePrice", java.math.BigDecimal.class);

    public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

    public QInsuranceRate(String variable) {
        this(InsuranceRate.class, forVariable(variable), INITS);
    }

    public QInsuranceRate(Path<? extends InsuranceRate> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QInsuranceRate(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QInsuranceRate(PathMetadata<?> metadata, PathInits inits) {
        this(InsuranceRate.class, metadata, inits);
    }

    public QInsuranceRate(Class<? extends InsuranceRate> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.insuranceActivity = inits.isInitialized("insuranceActivity") ? new QInsuranceActivity(forProperty("insuranceActivity"), inits.get("insuranceActivity")) : null;
    }

}

