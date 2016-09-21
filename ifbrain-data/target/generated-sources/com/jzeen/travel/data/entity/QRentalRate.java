package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QRentalRate is a Querydsl query type for RentalRate
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRentalRate extends EntityPathBase<RentalRate> {

    private static final long serialVersionUID = -705747998L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRentalRate rentalRate = new QRentalRate("rentalRate");

    public final NumberPath<java.math.BigDecimal> carratecost = createNumber("carratecost", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> carrateprice = createNumber("carrateprice", java.math.BigDecimal.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QRentalCar specialcar;

    public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

    public QRentalRate(String variable) {
        this(RentalRate.class, forVariable(variable), INITS);
    }

    public QRentalRate(Path<? extends RentalRate> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRentalRate(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRentalRate(PathMetadata<?> metadata, PathInits inits) {
        this(RentalRate.class, metadata, inits);
    }

    public QRentalRate(Class<? extends RentalRate> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.specialcar = inits.isInitialized("specialcar") ? new QRentalCar(forProperty("specialcar"), inits.get("specialcar")) : null;
    }

}

