package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QStandGuideView is a Querydsl query type for StandGuideView
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QStandGuideView extends EntityPathBase<StandGuideView> {

    private static final long serialVersionUID = 1762550089L;

    public static final QStandGuideView standGuideView = new QStandGuideView("standGuideView");

    public final StringPath cnName = createString("cnName");

    public final StringPath commisionPercentage = createString("commisionPercentage");

    public final DateTimePath<java.util.Date> endDate = createDateTime("endDate", java.util.Date.class);

    public final StringPath guideCarPrice = createString("guideCarPrice");

    public final NumberPath<Integer> guideCount = createNumber("guideCount", Integer.class);

    public final NumberPath<Integer> guidePk = createNumber("guidePk", Integer.class);

    public final NumberPath<java.math.BigDecimal> guidePrice = createNumber("guidePrice", java.math.BigDecimal.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> orderId = createNumber("orderId", Integer.class);

    public final DateTimePath<java.util.Date> startDate = createDateTime("startDate", java.util.Date.class);

    public QStandGuideView(String variable) {
        super(StandGuideView.class, forVariable(variable));
    }

    public QStandGuideView(Path<? extends StandGuideView> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStandGuideView(PathMetadata<?> metadata) {
        super(StandGuideView.class, metadata);
    }

}

