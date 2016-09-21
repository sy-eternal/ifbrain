package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QRentalPlan is a Querydsl query type for RentalPlan
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRentalPlan extends EntityPathBase<RentalPlan> {

    private static final long serialVersionUID = -705797589L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRentalPlan rentalPlan = new QRentalPlan("rentalPlan");

    public final NumberPath<java.math.BigDecimal> carprice = createNumber("carprice", java.math.BigDecimal.class);

    public final StringPath cartype = createString("cartype");

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final QDatePlan datePlan;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QRentalCar specialcar;

    public final NumberPath<java.math.BigDecimal> subtotalamount = createNumber("subtotalamount", java.math.BigDecimal.class);

    public final StringPath supplierNum = createString("supplierNum");

    public QRentalPlan(String variable) {
        this(RentalPlan.class, forVariable(variable), INITS);
    }

    public QRentalPlan(Path<? extends RentalPlan> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRentalPlan(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRentalPlan(PathMetadata<?> metadata, PathInits inits) {
        this(RentalPlan.class, metadata, inits);
    }

    public QRentalPlan(Class<? extends RentalPlan> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.datePlan = inits.isInitialized("datePlan") ? new QDatePlan(forProperty("datePlan"), inits.get("datePlan")) : null;
        this.specialcar = inits.isInitialized("specialcar") ? new QRentalCar(forProperty("specialcar"), inits.get("specialcar")) : null;
    }

}

