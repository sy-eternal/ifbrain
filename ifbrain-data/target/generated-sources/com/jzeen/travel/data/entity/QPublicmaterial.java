package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QPublicmaterial is a Querydsl query type for Publicmaterial
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPublicmaterial extends EntityPathBase<Publicmaterial> {

    private static final long serialVersionUID = -1041586354L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPublicmaterial publicmaterial = new QPublicmaterial("publicmaterial");

    public final StringPath author = createString("author");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final QHeadPortrait headPortrait;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QNavigationbarModule navigationbarmodule;

    public final SimplePath<java.sql.Blob> publicmaterialContent = createSimple("publicmaterialContent", java.sql.Blob.class);

    public final StringPath publicmaterialName = createString("publicmaterialName");

    public final NumberPath<Integer> visitornumber = createNumber("visitornumber", Integer.class);

    public QPublicmaterial(String variable) {
        this(Publicmaterial.class, forVariable(variable), INITS);
    }

    public QPublicmaterial(Path<? extends Publicmaterial> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPublicmaterial(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPublicmaterial(PathMetadata<?> metadata, PathInits inits) {
        this(Publicmaterial.class, metadata, inits);
    }

    public QPublicmaterial(Class<? extends Publicmaterial> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.headPortrait = inits.isInitialized("headPortrait") ? new QHeadPortrait(forProperty("headPortrait")) : null;
        this.navigationbarmodule = inits.isInitialized("navigationbarmodule") ? new QNavigationbarModule(forProperty("navigationbarmodule"), inits.get("navigationbarmodule")) : null;
    }

}

