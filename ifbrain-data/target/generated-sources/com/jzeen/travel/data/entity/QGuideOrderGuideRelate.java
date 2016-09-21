package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QGuideOrderGuideRelate is a Querydsl query type for GuideOrderGuideRelate
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QGuideOrderGuideRelate extends EntityPathBase<GuideOrderGuideRelate> {

    private static final long serialVersionUID = -347883035L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGuideOrderGuideRelate guideOrderGuideRelate = new QGuideOrderGuideRelate("guideOrderGuideRelate");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final QGuide guide;

    public final QGuideOrder guideOrder;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QGuideOrderGuideRelate(String variable) {
        this(GuideOrderGuideRelate.class, forVariable(variable), INITS);
    }

    public QGuideOrderGuideRelate(Path<? extends GuideOrderGuideRelate> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QGuideOrderGuideRelate(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QGuideOrderGuideRelate(PathMetadata<?> metadata, PathInits inits) {
        this(GuideOrderGuideRelate.class, metadata, inits);
    }

    public QGuideOrderGuideRelate(Class<? extends GuideOrderGuideRelate> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.guide = inits.isInitialized("guide") ? new QGuide(forProperty("guide"), inits.get("guide")) : null;
        this.guideOrder = inits.isInitialized("guideOrder") ? new QGuideOrder(forProperty("guideOrder"), inits.get("guideOrder")) : null;
    }

}

