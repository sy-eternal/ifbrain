package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCityPlan is a Querydsl query type for CityPlan
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCityPlan extends EntityPathBase<CityPlan> {

    private static final long serialVersionUID = 952818290L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCityPlan cityPlan = new QCityPlan("cityPlan");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final QDatePlan datePlan;

    public final QCity fromCity;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QCity toCity;

    public QCityPlan(String variable) {
        this(CityPlan.class, forVariable(variable), INITS);
    }

    public QCityPlan(Path<? extends CityPlan> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCityPlan(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCityPlan(PathMetadata<?> metadata, PathInits inits) {
        this(CityPlan.class, metadata, inits);
    }

    public QCityPlan(Class<? extends CityPlan> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.datePlan = inits.isInitialized("datePlan") ? new QDatePlan(forProperty("datePlan"), inits.get("datePlan")) : null;
        this.fromCity = inits.isInitialized("fromCity") ? new QCity(forProperty("fromCity"), inits.get("fromCity")) : null;
        this.toCity = inits.isInitialized("toCity") ? new QCity(forProperty("toCity"), inits.get("toCity")) : null;
    }

}

