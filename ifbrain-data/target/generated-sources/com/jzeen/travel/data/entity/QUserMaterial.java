package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QUserMaterial is a Querydsl query type for UserMaterial
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QUserMaterial extends EntityPathBase<UserMaterial> {

    private static final long serialVersionUID = 1777261136L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserMaterial userMaterial = new QUserMaterial("userMaterial");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QMaterial material;

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public final QUser user;

    public QUserMaterial(String variable) {
        this(UserMaterial.class, forVariable(variable), INITS);
    }

    public QUserMaterial(Path<? extends UserMaterial> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUserMaterial(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUserMaterial(PathMetadata<?> metadata, PathInits inits) {
        this(UserMaterial.class, metadata, inits);
    }

    public QUserMaterial(Class<? extends UserMaterial> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.material = inits.isInitialized("material") ? new QMaterial(forProperty("material"), inits.get("material")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

