package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QNavigationbarModule is a Querydsl query type for NavigationbarModule
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QNavigationbarModule extends EntityPathBase<NavigationbarModule> {

    private static final long serialVersionUID = -608092467L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QNavigationbarModule navigationbarModule = new QNavigationbarModule("navigationbarModule");

    public final StringPath author = createString("author");

    public final SimplePath<java.sql.Blob> content = createSimple("content", java.sql.Blob.class);

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final QHeadPortrait headPortrait;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QNavigationBar navigationBar;

    public final StringPath shorttitle = createString("shorttitle");

    public final StringPath title = createString("title");

    public final NumberPath<Integer> type = createNumber("type", Integer.class);

    public QNavigationbarModule(String variable) {
        this(NavigationbarModule.class, forVariable(variable), INITS);
    }

    public QNavigationbarModule(Path<? extends NavigationbarModule> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QNavigationbarModule(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QNavigationbarModule(PathMetadata<?> metadata, PathInits inits) {
        this(NavigationbarModule.class, metadata, inits);
    }

    public QNavigationbarModule(Class<? extends NavigationbarModule> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.headPortrait = inits.isInitialized("headPortrait") ? new QHeadPortrait(forProperty("headPortrait")) : null;
        this.navigationBar = inits.isInitialized("navigationBar") ? new QNavigationBar(forProperty("navigationBar")) : null;
    }

}

