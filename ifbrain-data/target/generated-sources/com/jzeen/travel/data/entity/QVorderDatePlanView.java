package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QVorderDatePlanView is a Querydsl query type for VorderDatePlanView
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QVorderDatePlanView extends EntityPathBase<VorderDatePlanView> {

    private static final long serialVersionUID = -32551502L;

    public static final QVorderDatePlanView vorderDatePlanView = new QVorderDatePlanView("vorderDatePlanView");

    public final DateTimePath<java.util.Date> endDate = createDateTime("endDate", java.util.Date.class);

    public final StringPath feedback = createString("feedback");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DateTimePath<java.util.Date> startDate = createDateTime("startDate", java.util.Date.class);

    public final NumberPath<Integer> tOrderId = createNumber("tOrderId", Integer.class);

    public QVorderDatePlanView(String variable) {
        super(VorderDatePlanView.class, forVariable(variable));
    }

    public QVorderDatePlanView(Path<? extends VorderDatePlanView> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVorderDatePlanView(PathMetadata<?> metadata) {
        super(VorderDatePlanView.class, metadata);
    }

}

