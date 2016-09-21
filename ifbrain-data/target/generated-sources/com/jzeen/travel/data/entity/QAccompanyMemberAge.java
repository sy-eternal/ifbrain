package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QAccompanyMemberAge is a Querydsl query type for AccompanyMemberAge
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QAccompanyMemberAge extends EntityPathBase<AccompanyMemberAge> {

    private static final long serialVersionUID = -162670104L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAccompanyMemberAge accompanyMemberAge = new QAccompanyMemberAge("accompanyMemberAge");

    public final StringPath accompanymemberage = createString("accompanymemberage");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QOrder order;

    public QAccompanyMemberAge(String variable) {
        this(AccompanyMemberAge.class, forVariable(variable), INITS);
    }

    public QAccompanyMemberAge(Path<? extends AccompanyMemberAge> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAccompanyMemberAge(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAccompanyMemberAge(PathMetadata<?> metadata, PathInits inits) {
        this(AccompanyMemberAge.class, metadata, inits);
    }

    public QAccompanyMemberAge(Class<? extends AccompanyMemberAge> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.order = inits.isInitialized("order") ? new QOrder(forProperty("order"), inits.get("order")) : null;
    }

}

