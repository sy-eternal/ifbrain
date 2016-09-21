package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QGuideGuidetypePlanRelate is a Querydsl query type for GuideGuidetypePlanRelate
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QGuideGuidetypePlanRelate extends EntityPathBase<GuideGuidetypePlanRelate> {

    private static final long serialVersionUID = -302938278L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGuideGuidetypePlanRelate guideGuidetypePlanRelate = new QGuideGuidetypePlanRelate("guideGuidetypePlanRelate");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final QGuide guide;

    public final QGuideTypePlan guideTypePlan;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QGuideGuidetypePlanRelate(String variable) {
        this(GuideGuidetypePlanRelate.class, forVariable(variable), INITS);
    }

    public QGuideGuidetypePlanRelate(Path<? extends GuideGuidetypePlanRelate> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QGuideGuidetypePlanRelate(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QGuideGuidetypePlanRelate(PathMetadata<?> metadata, PathInits inits) {
        this(GuideGuidetypePlanRelate.class, metadata, inits);
    }

    public QGuideGuidetypePlanRelate(Class<? extends GuideGuidetypePlanRelate> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.guide = inits.isInitialized("guide") ? new QGuide(forProperty("guide"), inits.get("guide")) : null;
        this.guideTypePlan = inits.isInitialized("guideTypePlan") ? new QGuideTypePlan(forProperty("guideTypePlan"), inits.get("guideTypePlan")) : null;
    }

}

