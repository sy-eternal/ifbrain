package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QIfbrainNumber is a Querydsl query type for IfbrainNumber
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QIfbrainNumber extends EntityPathBase<IfbrainNumber> {

    private static final long serialVersionUID = -218236892L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QIfbrainNumber ifbrainNumber = new QIfbrainNumber("ifbrainNumber");

    public final StringPath brainnumber = createString("brainnumber");

    public final QChild child;

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QIfbrainNumber(String variable) {
        this(IfbrainNumber.class, forVariable(variable), INITS);
    }

    public QIfbrainNumber(Path<? extends IfbrainNumber> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QIfbrainNumber(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QIfbrainNumber(PathMetadata<?> metadata, PathInits inits) {
        this(IfbrainNumber.class, metadata, inits);
    }

    public QIfbrainNumber(Class<? extends IfbrainNumber> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.child = inits.isInitialized("child") ? new QChild(forProperty("child"), inits.get("child")) : null;
    }

}

