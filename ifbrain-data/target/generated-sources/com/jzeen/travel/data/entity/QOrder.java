package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QOrder is a Querydsl query type for Order
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QOrder extends EntityPathBase<Order> {

    private static final long serialVersionUID = -1653796656L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrder order = new QOrder("order1");

    public final ListPath<AccompanyMemberAge, QAccompanyMemberAge> accompanyMemberAge = this.<AccompanyMemberAge, QAccompanyMemberAge>createList("accompanyMemberAge", AccompanyMemberAge.class, QAccompanyMemberAge.class, PathInits.DIRECT2);

    public final ListPath<AccompanyRelate, QAccompanyRelate> accompanyRelates = this.<AccompanyRelate, QAccompanyRelate>createList("accompanyRelates", AccompanyRelate.class, QAccompanyRelate.class, PathInits.DIRECT2);

    public final StringPath activityCode = createString("activityCode");

    public final NumberPath<Integer> activityStatus = createNumber("activityStatus", Integer.class);

    public final NumberPath<Integer> amountSetailStatus = createNumber("amountSetailStatus", Integer.class);

    public final QCity backCity;

    public final NumberPath<java.math.BigDecimal> checkOutAmount = createNumber("checkOutAmount", java.math.BigDecimal.class);

    public final ListPath<CityConfirmed, QCityConfirmed> cityConfirmeds = this.<CityConfirmed, QCityConfirmed>createList("cityConfirmeds", CityConfirmed.class, QCityConfirmed.class, PathInits.DIRECT2);

    public final DateTimePath<java.util.Date> commitOrderTime = createDateTime("commitOrderTime", java.util.Date.class);

    public final ListPath<ConfiremedTraveler, QConfiremedTraveler> confiremedTravelers = this.<ConfiremedTraveler, QConfiremedTraveler>createList("confiremedTravelers", ConfiremedTraveler.class, QConfiremedTraveler.class, PathInits.DIRECT2);

    public final NumberPath<Integer> confirmedCityStatus = createNumber("confirmedCityStatus", Integer.class);

    public final QCountry country;

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> dateConfirm = createNumber("dateConfirm", Integer.class);

    public final ListPath<DatePlan, QDatePlan> datePlan = this.<DatePlan, QDatePlan>createList("datePlan", DatePlan.class, QDatePlan.class, PathInits.DIRECT2);

    public final NumberPath<Integer> duration = createNumber("duration", Integer.class);

    public final DateTimePath<java.util.Date> endDate = createDateTime("endDate", java.util.Date.class);

    public final NumberPath<java.math.BigDecimal> exchangerate = createNumber("exchangerate", java.math.BigDecimal.class);

    public final NumberPath<Integer> hotelRoomCount = createNumber("hotelRoomCount", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final ListPath<InnerCityTrafficRelate, QInnerCityTrafficRelate> innerCityTrafficRelates = this.<InnerCityTrafficRelate, QInnerCityTrafficRelate>createList("innerCityTrafficRelates", InnerCityTrafficRelate.class, QInnerCityTrafficRelate.class, PathInits.DIRECT2);

    public final ListPath<InsurancePlan, QInsurancePlan> insurancePlan = this.<InsurancePlan, QInsurancePlan>createList("insurancePlan", InsurancePlan.class, QInsurancePlan.class, PathInits.DIRECT2);

    public final ListPath<InterestedCityRelate, QInterestedCityRelate> interestedCityRelates = this.<InterestedCityRelate, QInterestedCityRelate>createList("interestedCityRelates", InterestedCityRelate.class, QInterestedCityRelate.class, PathInits.DIRECT2);

    public final NumberPath<Integer> interestedCityStatus = createNumber("interestedCityStatus", Integer.class);

    public final ListPath<MarginPlan, QMarginPlan> marginplan = this.<MarginPlan, QMarginPlan>createList("marginplan", MarginPlan.class, QMarginPlan.class, PathInits.DIRECT2);

    public final NumberPath<java.math.BigDecimal> orderAmount = createNumber("orderAmount", java.math.BigDecimal.class);

    public final StringPath orderNumber = createString("orderNumber");

    public final NumberPath<Integer> orderStatus = createNumber("orderStatus", Integer.class);

    public final NumberPath<Integer> personCount = createNumber("personCount", Integer.class);

    public final QPlanOrder planOrder;

    public final ListPath<PurposeRelate, QPurposeRelate> purposeRelates = this.<PurposeRelate, QPurposeRelate>createList("purposeRelates", PurposeRelate.class, QPurposeRelate.class, PathInits.DIRECT2);

    public final StringPath remark = createString("remark");

    public final QCity startCity;

    public final DateTimePath<java.util.Date> startDate = createDateTime("startDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> statusTime = createDateTime("statusTime", java.util.Date.class);

    public final ListPath<ThemeRelate, QThemeRelate> themeRelates = this.<ThemeRelate, QThemeRelate>createList("themeRelates", ThemeRelate.class, QThemeRelate.class, PathInits.DIRECT2);

    public final QUser traveler;

    public QOrder(String variable) {
        this(Order.class, forVariable(variable), INITS);
    }

    public QOrder(Path<? extends Order> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrder(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrder(PathMetadata<?> metadata, PathInits inits) {
        this(Order.class, metadata, inits);
    }

    public QOrder(Class<? extends Order> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.backCity = inits.isInitialized("backCity") ? new QCity(forProperty("backCity"), inits.get("backCity")) : null;
        this.country = inits.isInitialized("country") ? new QCountry(forProperty("country")) : null;
        this.planOrder = inits.isInitialized("planOrder") ? new QPlanOrder(forProperty("planOrder"), inits.get("planOrder")) : null;
        this.startCity = inits.isInitialized("startCity") ? new QCity(forProperty("startCity"), inits.get("startCity")) : null;
        this.traveler = inits.isInitialized("traveler") ? new QUser(forProperty("traveler"), inits.get("traveler")) : null;
    }

}

