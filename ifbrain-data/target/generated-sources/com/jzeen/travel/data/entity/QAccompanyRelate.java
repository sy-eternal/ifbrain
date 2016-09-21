package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QAccompanyRelate is a Querydsl query type for AccompanyRelate
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QAccompanyRelate extends EntityPathBase<AccompanyRelate> {

    private static final long serialVersionUID = 1366536278L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAccompanyRelate accompanyRelate = new QAccompanyRelate("accompanyRelate");

    public final QAccompanyType accompany;

    public final StringPath accompanyType = createString("accompanyType");

    public final NumberPath<Integer> accompanyTypePk = createNumber("accompanyTypePk", Integer.class);

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QOrder order;

    public final NumberPath<Integer> orderPk = createNumber("orderPk", Integer.class);

    public QAccompanyRelate(String variable) {
        this(AccompanyRelate.class, forVariable(variable), INITS);
    }

    public QAccompanyRelate(Path<? extends AccompanyRelate> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAccompanyRelate(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAccompanyRelate(PathMetadata<?> metadata, PathInits inits) {
        this(AccompanyRelate.class, metadata, inits);
    }

    public QAccompanyRelate(Class<? extends AccompanyRelate> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.accompany = inits.isInitialized("accompany") ? new QAccompanyType(forProperty("accompany")) : null;
        this.order = inits.isInitialized("order") ? new QOrder(forProperty("order"), inits.get("order")) : null;
    }

}

