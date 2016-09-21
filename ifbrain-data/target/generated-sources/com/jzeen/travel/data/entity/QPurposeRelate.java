package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QPurposeRelate is a Querydsl query type for PurposeRelate
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPurposeRelate extends EntityPathBase<PurposeRelate> {

    private static final long serialVersionUID = -1508421223L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPurposeRelate purposeRelate = new QPurposeRelate("purposeRelate");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QOrder order;

    public final QPurpose purpose;

    public final StringPath purposeName = createString("purposeName");

    public QPurposeRelate(String variable) {
        this(PurposeRelate.class, forVariable(variable), INITS);
    }

    public QPurposeRelate(Path<? extends PurposeRelate> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPurposeRelate(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPurposeRelate(PathMetadata<?> metadata, PathInits inits) {
        this(PurposeRelate.class, metadata, inits);
    }

    public QPurposeRelate(Class<? extends PurposeRelate> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.order = inits.isInitialized("order") ? new QOrder(forProperty("order"), inits.get("order")) : null;
        this.purpose = inits.isInitialized("purpose") ? new QPurpose(forProperty("purpose")) : null;
    }

}

