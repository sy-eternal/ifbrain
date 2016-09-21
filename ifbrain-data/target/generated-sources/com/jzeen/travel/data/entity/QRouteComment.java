package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QRouteComment is a Querydsl query type for RouteComment
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRouteComment extends EntityPathBase<RouteComment> {

    private static final long serialVersionUID = -294748140L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRouteComment routeComment = new QRouteComment("routeComment");

    public final StringPath comments = createString("comments");

    public final DateTimePath<java.util.Date> createtime = createDateTime("createtime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QRoute route;

    public final QUser user;

    public QRouteComment(String variable) {
        this(RouteComment.class, forVariable(variable), INITS);
    }

    public QRouteComment(Path<? extends RouteComment> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRouteComment(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRouteComment(PathMetadata<?> metadata, PathInits inits) {
        this(RouteComment.class, metadata, inits);
    }

    public QRouteComment(Class<? extends RouteComment> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.route = inits.isInitialized("route") ? new QRoute(forProperty("route"), inits.get("route")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

