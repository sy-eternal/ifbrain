package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QInsuranceActivity is a Querydsl query type for InsuranceActivity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QInsuranceActivity extends EntityPathBase<InsuranceActivity> {

    private static final long serialVersionUID = 1365301899L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInsuranceActivity insuranceActivity = new QInsuranceActivity("insuranceActivity");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath insuranceCove = createString("insuranceCove");

    public final StringPath insuranceName = createString("insuranceName");

    public final ListPath<InsuranceRate, QInsuranceRate> insuranceRate = this.<InsuranceRate, QInsuranceRate>createList("insuranceRate", InsuranceRate.class, QInsuranceRate.class, PathInits.DIRECT2);

    public final StringPath insuranceType = createString("insuranceType");

    public final QSupplier supplier;

    public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

    public QInsuranceActivity(String variable) {
        this(InsuranceActivity.class, forVariable(variable), INITS);
    }

    public QInsuranceActivity(Path<? extends InsuranceActivity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QInsuranceActivity(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QInsuranceActivity(PathMetadata<?> metadata, PathInits inits) {
        this(InsuranceActivity.class, metadata, inits);
    }

    public QInsuranceActivity(Class<? extends InsuranceActivity> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.supplier = inits.isInitialized("supplier") ? new QSupplier(forProperty("supplier")) : null;
    }

}

