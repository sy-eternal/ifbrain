package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QGuideCar is a Querydsl query type for GuideCar
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QGuideCar extends EntityPathBase<GuideCar> {

    private static final long serialVersionUID = 1060506230L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGuideCar guideCar = new QGuideCar("guideCar");

    public final NumberPath<Integer> approvalStatus = createNumber("approvalStatus", Integer.class);

    public final StringPath carType = createString("carType");

    public final StringPath classes = createString("classes");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> excursionStatus = createNumber("excursionStatus", Integer.class);

    public final QGuideCarManage guideCarManage;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QImage image;

    public final StringPath productionDate = createString("productionDate");

    public final QGuide user;

    public QGuideCar(String variable) {
        this(GuideCar.class, forVariable(variable), INITS);
    }

    public QGuideCar(Path<? extends GuideCar> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QGuideCar(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QGuideCar(PathMetadata<?> metadata, PathInits inits) {
        this(GuideCar.class, metadata, inits);
    }

    public QGuideCar(Class<? extends GuideCar> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.guideCarManage = inits.isInitialized("guideCarManage") ? new QGuideCarManage(forProperty("guideCarManage")) : null;
        this.image = inits.isInitialized("image") ? new QImage(forProperty("image"), inits.get("image")) : null;
        this.user = inits.isInitialized("user") ? new QGuide(forProperty("user"), inits.get("user")) : null;
    }

}

