package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QIfbrainVar is a Querydsl query type for IfbrainVar
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QIfbrainVar extends EntityPathBase<IfbrainVar> {

    private static final long serialVersionUID = -1960423380L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QIfbrainVar ifbrainVar = new QIfbrainVar("ifbrainVar");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QIfbrainIndex ifbrainIndex;

    public final NumberPath<Integer> incomeType = createNumber("incomeType", Integer.class);

    public final StringPath varName = createString("varName");

    public final StringPath varValue = createString("varValue");

    public QIfbrainVar(String variable) {
        this(IfbrainVar.class, forVariable(variable), INITS);
    }

    public QIfbrainVar(Path<? extends IfbrainVar> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QIfbrainVar(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QIfbrainVar(PathMetadata<?> metadata, PathInits inits) {
        this(IfbrainVar.class, metadata, inits);
    }

    public QIfbrainVar(Class<? extends IfbrainVar> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.ifbrainIndex = inits.isInitialized("ifbrainIndex") ? new QIfbrainIndex(forProperty("ifbrainIndex"), inits.get("ifbrainIndex")) : null;
    }

}

