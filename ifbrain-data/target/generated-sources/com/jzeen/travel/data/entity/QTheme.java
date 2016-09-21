package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QTheme is a Querydsl query type for Theme
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTheme extends EntityPathBase<Theme> {

    private static final long serialVersionUID = -1649475765L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTheme theme1 = new QTheme("theme1");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QImage image;

    public final StringPath theme = createString("theme");

    public QTheme(String variable) {
        this(Theme.class, forVariable(variable), INITS);
    }

    public QTheme(Path<? extends Theme> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTheme(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTheme(PathMetadata<?> metadata, PathInits inits) {
        this(Theme.class, metadata, inits);
    }

    public QTheme(Class<? extends Theme> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.image = inits.isInitialized("image") ? new QImage(forProperty("image"), inits.get("image")) : null;
    }

}

