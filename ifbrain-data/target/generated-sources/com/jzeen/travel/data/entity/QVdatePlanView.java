package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QVdatePlanView is a Querydsl query type for VdatePlanView
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QVdatePlanView extends EntityPathBase<VdatePlanView> {

    private static final long serialVersionUID = 909584948L;

    public static final QVdatePlanView vdatePlanView = new QVdatePlanView("vdatePlanView");

    public final DateTimePath<java.util.Date> endDate = createDateTime("endDate", java.util.Date.class);

    public final StringPath feedback = createString("feedback");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> otTime = createNumber("otTime", Integer.class);

    public final DateTimePath<java.util.Date> startDate = createDateTime("startDate", java.util.Date.class);

    public final NumberPath<Integer> tOrderId = createNumber("tOrderId", Integer.class);

    public QVdatePlanView(String variable) {
        super(VdatePlanView.class, forVariable(variable));
    }

    public QVdatePlanView(Path<? extends VdatePlanView> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVdatePlanView(PathMetadata<?> metadata) {
        super(VdatePlanView.class, metadata);
    }

}

