package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QGuideImageRelate is a Querydsl query type for GuideImageRelate
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QGuideImageRelate extends EntityPathBase<GuideImageRelate> {

    private static final long serialVersionUID = 24575286L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGuideImageRelate guideImageRelate = new QGuideImageRelate("guideImageRelate");

    public final DateTimePath<java.util.Date> createtime = createDateTime("createtime", java.util.Date.class);

    public final QGuide guide;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QImage image;

    public final NumberPath<Integer> type = createNumber("type", Integer.class);

    public QGuideImageRelate(String variable) {
        this(GuideImageRelate.class, forVariable(variable), INITS);
    }

    public QGuideImageRelate(Path<? extends GuideImageRelate> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QGuideImageRelate(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QGuideImageRelate(PathMetadata<?> metadata, PathInits inits) {
        this(GuideImageRelate.class, metadata, inits);
    }

    public QGuideImageRelate(Class<? extends GuideImageRelate> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.guide = inits.isInitialized("guide") ? new QGuide(forProperty("guide"), inits.get("guide")) : null;
        this.image = inits.isInitialized("image") ? new QImage(forProperty("image"), inits.get("image")) : null;
    }

}

