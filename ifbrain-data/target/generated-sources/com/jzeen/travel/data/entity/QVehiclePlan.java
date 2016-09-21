package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QVehiclePlan is a Querydsl query type for VehiclePlan
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QVehiclePlan extends EntityPathBase<VehiclePlan> {

    private static final long serialVersionUID = -1240401801L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVehiclePlan vehiclePlan = new QVehiclePlan("vehiclePlan");

    public final StringPath airEquipType = createString("airEquipType");

    public final StringPath airline = createString("airline");

    public final StringPath arrivalTerminalId = createString("arrivalTerminalId");

    public final DateTimePath<java.util.Date> arrivalTime = createDateTime("arrivalTime", java.util.Date.class);

    public final StringPath arriveAirport = createString("arriveAirport");

    public final NumberPath<java.math.BigDecimal> costPrice = createNumber("costPrice", java.math.BigDecimal.class);

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final QDatePlan datePlan;

    public final StringPath departureTerminalId = createString("departureTerminalId");

    public final DateTimePath<java.util.Date> departureTime = createDateTime("departureTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> personCount = createNumber("personCount", Integer.class);

    public final StringPath rank = createString("rank");

    public final NumberPath<java.math.BigDecimal> salePrice = createNumber("salePrice", java.math.BigDecimal.class);

    public final StringPath startAirport = createString("startAirport");

    public final StringPath supplierOrderNumber = createString("supplierOrderNumber");

    public final QSupplierPriceRule supplierPriceRule;

    public final StringPath vehicleNumber = createString("vehicleNumber");

    public final NumberPath<Integer> vehicleTypeCode = createNumber("vehicleTypeCode", Integer.class);

    public QVehiclePlan(String variable) {
        this(VehiclePlan.class, forVariable(variable), INITS);
    }

    public QVehiclePlan(Path<? extends VehiclePlan> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVehiclePlan(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVehiclePlan(PathMetadata<?> metadata, PathInits inits) {
        this(VehiclePlan.class, metadata, inits);
    }

    public QVehiclePlan(Class<? extends VehiclePlan> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.datePlan = inits.isInitialized("datePlan") ? new QDatePlan(forProperty("datePlan"), inits.get("datePlan")) : null;
        this.supplierPriceRule = inits.isInitialized("supplierPriceRule") ? new QSupplierPriceRule(forProperty("supplierPriceRule"), inits.get("supplierPriceRule")) : null;
    }

}

