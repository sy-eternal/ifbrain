package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QAccompanyActionType is a Querydsl query type for AccompanyActionType
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QAccompanyActionType extends EntityPathBase<AccompanyActionType> {

    private static final long serialVersionUID = -1849159411L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAccompanyActionType accompanyActionType = new QAccompanyActionType("accompanyActionType");

    public final StringPath accompanyType = createString("accompanyType");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QImage image;

    public QAccompanyActionType(String variable) {
        this(AccompanyActionType.class, forVariable(variable), INITS);
    }

    public QAccompanyActionType(Path<? extends AccompanyActionType> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAccompanyActionType(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAccompanyActionType(PathMetadata<?> metadata, PathInits inits) {
        this(AccompanyActionType.class, metadata, inits);
    }

    public QAccompanyActionType(Class<? extends AccompanyActionType> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.image = inits.isInitialized("image") ? new QImage(forProperty("image"), inits.get("image")) : null;
    }

}

