package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QRentalCar is a Querydsl query type for RentalCar
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRentalCar extends EntityPathBase<RentalCar> {

    private static final long serialVersionUID = -1408253806L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRentalCar rentalCar = new QRentalCar("rentalCar");

    public final StringPath cartype = createString("cartype");

    public final StringPath count = createString("count");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final ListPath<RentalPlan, QRentalPlan> SpecialCarPlan = this.<RentalPlan, QRentalPlan>createList("SpecialCarPlan", RentalPlan.class, QRentalPlan.class, PathInits.DIRECT2);

    public final ListPath<RentalRate, QRentalRate> specialCarRate = this.<RentalRate, QRentalRate>createList("specialCarRate", RentalRate.class, QRentalRate.class, PathInits.DIRECT2);

    public final QSupplier supplier;

    public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

    public QRentalCar(String variable) {
        this(RentalCar.class, forVariable(variable), INITS);
    }

    public QRentalCar(Path<? extends RentalCar> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRentalCar(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRentalCar(PathMetadata<?> metadata, PathInits inits) {
        this(RentalCar.class, metadata, inits);
    }

    public QRentalCar(Class<? extends RentalCar> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.supplier = inits.isInitialized("supplier") ? new QSupplier(forProperty("supplier")) : null;
    }

}

