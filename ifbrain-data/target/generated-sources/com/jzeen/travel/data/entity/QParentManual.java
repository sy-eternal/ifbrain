package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QParentManual is a Querydsl query type for ParentManual
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QParentManual extends EntityPathBase<ParentManual> {

    private static final long serialVersionUID = -1174842034L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QParentManual parentManual = new QParentManual("parentManual");

    public final StringPath author = createString("author");

    public final QCourse course;

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final SimplePath<java.sql.Blob> manualContent = createSimple("manualContent", java.sql.Blob.class);

    public final StringPath manualName = createString("manualName");

    public final NumberPath<Integer> ordinalNumber = createNumber("ordinalNumber", Integer.class);

    public final QParentManualImage parentmanualimage;

    public final NumberPath<Integer> visitornumber = createNumber("visitornumber", Integer.class);

    public QParentManual(String variable) {
        this(ParentManual.class, forVariable(variable), INITS);
    }

    public QParentManual(Path<? extends ParentManual> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QParentManual(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QParentManual(PathMetadata<?> metadata, PathInits inits) {
        this(ParentManual.class, metadata, inits);
    }

    public QParentManual(Class<? extends ParentManual> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.course = inits.isInitialized("course") ? new QCourse(forProperty("course")) : null;
        this.parentmanualimage = inits.isInitialized("parentmanualimage") ? new QParentManualImage(forProperty("parentmanualimage")) : null;
    }

}

