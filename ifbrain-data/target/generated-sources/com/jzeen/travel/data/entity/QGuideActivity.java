package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QGuideActivity is a Querydsl query type for GuideActivity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QGuideActivity extends EntityPathBase<GuideActivity> {

    private static final long serialVersionUID = 1338949037L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGuideActivity guideActivity = new QGuideActivity("guideActivity");

    public final QCity city;

    public final StringPath guideActivityType = createString("guideActivityType");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QSupplier supplier;

    public final DateTimePath<java.util.Date> update_time = createDateTime("update_time", java.util.Date.class);

    public QGuideActivity(String variable) {
        this(GuideActivity.class, forVariable(variable), INITS);
    }

    public QGuideActivity(Path<? extends GuideActivity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QGuideActivity(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QGuideActivity(PathMetadata<?> metadata, PathInits inits) {
        this(GuideActivity.class, metadata, inits);
    }

    public QGuideActivity(Class<? extends GuideActivity> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.city = inits.isInitialized("city") ? new QCity(forProperty("city"), inits.get("city")) : null;
        this.supplier = inits.isInitialized("supplier") ? new QSupplier(forProperty("supplier")) : null;
    }

}

