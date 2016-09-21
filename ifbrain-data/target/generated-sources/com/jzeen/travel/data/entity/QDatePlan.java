package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QDatePlan is a Querydsl query type for DatePlan
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDatePlan extends EntityPathBase<DatePlan> {

    private static final long serialVersionUID = -127903947L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDatePlan datePlan = new QDatePlan("datePlan");

    public final QCityPlan cityPlan;

    public final NumberPath<Integer> confirmStatus = createNumber("confirmStatus", Integer.class);

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final DateTimePath<java.util.Date> date = createDateTime("date", java.util.Date.class);

    public final DateTimePath<java.util.Date> endDate = createDateTime("endDate", java.util.Date.class);

    public final ListPath<ExcursionGuidePlan, QExcursionGuidePlan> excursionGuidePlans = this.<ExcursionGuidePlan, QExcursionGuidePlan>createList("excursionGuidePlans", ExcursionGuidePlan.class, QExcursionGuidePlan.class, PathInits.DIRECT2);

    public final StringPath feedback = createString("feedback");

    public final ListPath<FilghtPlan, QFilghtPlan> filghtPlan = this.<FilghtPlan, QFilghtPlan>createList("filghtPlan", FilghtPlan.class, QFilghtPlan.class, PathInits.DIRECT2);

    public final ListPath<GuideActivityPlan, QGuideActivityPlan> guideActivityPlans = this.<GuideActivityPlan, QGuideActivityPlan>createList("guideActivityPlans", GuideActivityPlan.class, QGuideActivityPlan.class, PathInits.DIRECT2);

    public final ListPath<HotelPlan, QHotelPlan> hotelPlan = this.<HotelPlan, QHotelPlan>createList("hotelPlan", HotelPlan.class, QHotelPlan.class, PathInits.DIRECT2);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QOrder order;

    public final StringPath ordinatedday = createString("ordinatedday");

    public final StringPath remark = createString("remark");

    public final ListPath<RentalPlan, QRentalPlan> rentalPlan = this.<RentalPlan, QRentalPlan>createList("rentalPlan", RentalPlan.class, QRentalPlan.class, PathInits.DIRECT2);

    public final ListPath<RentalPlan, QRentalPlan> specialCarPlan = this.<RentalPlan, QRentalPlan>createList("specialCarPlan", RentalPlan.class, QRentalPlan.class, PathInits.DIRECT2);

    public final ListPath<SpotPlan, QSpotPlan> spotPlan = this.<SpotPlan, QSpotPlan>createList("spotPlan", SpotPlan.class, QSpotPlan.class, PathInits.DIRECT2);

    public final ListPath<StandardGuidePlan, QStandardGuidePlan> standardGuidePlans = this.<StandardGuidePlan, QStandardGuidePlan>createList("standardGuidePlans", StandardGuidePlan.class, QStandardGuidePlan.class, PathInits.DIRECT2);

    public final DateTimePath<java.util.Date> startDate = createDateTime("startDate", java.util.Date.class);

    public final NumberPath<java.math.BigDecimal> subTotal = createNumber("subTotal", java.math.BigDecimal.class);

    public final QVehiclePlan vehiclePlan;

    public QDatePlan(String variable) {
        this(DatePlan.class, forVariable(variable), INITS);
    }

    public QDatePlan(Path<? extends DatePlan> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDatePlan(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDatePlan(PathMetadata<?> metadata, PathInits inits) {
        this(DatePlan.class, metadata, inits);
    }

    public QDatePlan(Class<? extends DatePlan> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cityPlan = inits.isInitialized("cityPlan") ? new QCityPlan(forProperty("cityPlan"), inits.get("cityPlan")) : null;
        this.order = inits.isInitialized("order") ? new QOrder(forProperty("order"), inits.get("order")) : null;
        this.vehiclePlan = inits.isInitialized("vehiclePlan") ? new QVehiclePlan(forProperty("vehiclePlan"), inits.get("vehiclePlan")) : null;
    }

}

