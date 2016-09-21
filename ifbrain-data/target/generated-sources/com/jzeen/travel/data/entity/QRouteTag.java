package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QRouteTag is a Querydsl query type for RouteTag
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRouteTag extends EntityPathBase<RouteTag> {

    private static final long serialVersionUID = -1915128881L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRouteTag routeTag = new QRouteTag("routeTag");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QRoute route;

    public final StringPath tag = createString("tag");

    public QRouteTag(String variable) {
        this(RouteTag.class, forVariable(variable), INITS);
    }

    public QRouteTag(Path<? extends RouteTag> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRouteTag(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRouteTag(PathMetadata<?> metadata, PathInits inits) {
        this(RouteTag.class, metadata, inits);
    }

    public QRouteTag(Class<? extends RouteTag> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.route = inits.isInitialized("route") ? new QRoute(forProperty("route"), inits.get("route")) : null;
    }

}

