package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QPurposeActive is a Querydsl query type for PurposeActive
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPurposeActive extends EntityPathBase<PurposeActive> {

    private static final long serialVersionUID = -1996717754L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPurposeActive purposeActive = new QPurposeActive("purposeActive");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QImage image;

    public final StringPath purposeactive = createString("purposeactive");

    public QPurposeActive(String variable) {
        this(PurposeActive.class, forVariable(variable), INITS);
    }

    public QPurposeActive(Path<? extends PurposeActive> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPurposeActive(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPurposeActive(PathMetadata<?> metadata, PathInits inits) {
        this(PurposeActive.class, metadata, inits);
    }

    public QPurposeActive(Class<? extends PurposeActive> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.image = inits.isInitialized("image") ? new QImage(forProperty("image"), inits.get("image")) : null;
    }

}

