package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QVcustomerReportView is a Querydsl query type for VcustomerReportView
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QVcustomerReportView extends EntityPathBase<VcustomerReportView> {

    private static final long serialVersionUID = 293582191L;

    public static final QVcustomerReportView vcustomerReportView = new QVcustomerReportView("vcustomerReportView");

    public final NumberPath<Integer> datePlanId = createNumber("datePlanId", Integer.class);

    public final DateTimePath<java.util.Date> endDate = createDateTime("endDate", java.util.Date.class);

    public final StringPath fromCityName = createString("fromCityName");

    public final NumberPath<Integer> fromCtiyId = createNumber("fromCtiyId", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> orderId = createNumber("orderId", Integer.class);

    public final NumberPath<Integer> personCount = createNumber("personCount", Integer.class);

    public final NumberPath<java.math.BigDecimal> salePrice = createNumber("salePrice", java.math.BigDecimal.class);

    public final NumberPath<Integer> seq = createNumber("seq", Integer.class);

    public final DateTimePath<java.util.Date> startDate = createDateTime("startDate", java.util.Date.class);

    public final StringPath toCityName = createString("toCityName");

    public final NumberPath<Integer> toCtiyId = createNumber("toCtiyId", Integer.class);

    public final StringPath typeCode = createString("typeCode");

    public final NumberPath<Integer> typeCodeId = createNumber("typeCodeId", Integer.class);

    public final StringPath typeName = createString("typeName");

    public QVcustomerReportView(String variable) {
        super(VcustomerReportView.class, forVariable(variable));
    }

    public QVcustomerReportView(Path<? extends VcustomerReportView> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVcustomerReportView(PathMetadata<?> metadata) {
        super(VcustomerReportView.class, metadata);
    }

}

