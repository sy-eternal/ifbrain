package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QExcursionGuideView is a Querydsl query type for ExcursionGuideView
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QExcursionGuideView extends EntityPathBase<ExcursionGuideView> {

    private static final long serialVersionUID = 2114842301L;

    public static final QExcursionGuideView excursionGuideView = new QExcursionGuideView("excursionGuideView");

    public final StringPath cnName = createString("cnName");

    public final DateTimePath<java.util.Date> endDate = createDateTime("endDate", java.util.Date.class);

    public final StringPath guideCarPrice = createString("guideCarPrice");

    public final NumberPath<Integer> guideCount = createNumber("guideCount", Integer.class);

    public final NumberPath<Integer> guidePk = createNumber("guidePk", Integer.class);

    public final NumberPath<java.math.BigDecimal> guidePrice = createNumber("guidePrice", java.math.BigDecimal.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> orderId = createNumber("orderId", Integer.class);

    public final DateTimePath<java.util.Date> startDate = createDateTime("startDate", java.util.Date.class);

    public QExcursionGuideView(String variable) {
        super(ExcursionGuideView.class, forVariable(variable));
    }

    public QExcursionGuideView(Path<? extends ExcursionGuideView> path) {
        super(path.getType(), path.getMetadata());
    }

    public QExcursionGuideView(PathMetadata<?> metadata) {
        super(ExcursionGuideView.class, metadata);
    }

}

