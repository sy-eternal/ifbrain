package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QWorkTaskRelate is a Querydsl query type for WorkTaskRelate
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QWorkTaskRelate extends EntityPathBase<WorkTaskRelate> {

    private static final long serialVersionUID = 1372406765L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWorkTaskRelate workTaskRelate = new QWorkTaskRelate("workTaskRelate");

    public final QChild child;

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final QEasyTask easyTask;

    public final NumberPath<java.math.BigDecimal> everytimesmoney = createNumber("everytimesmoney", java.math.BigDecimal.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public final QTask task;

    public final NumberPath<Integer> timeAdd = createNumber("timeAdd", Integer.class);

    public final NumberPath<Integer> times = createNumber("times", Integer.class);

    public final QUser user;

    public QWorkTaskRelate(String variable) {
        this(WorkTaskRelate.class, forVariable(variable), INITS);
    }

    public QWorkTaskRelate(Path<? extends WorkTaskRelate> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QWorkTaskRelate(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QWorkTaskRelate(PathMetadata<?> metadata, PathInits inits) {
        this(WorkTaskRelate.class, metadata, inits);
    }

    public QWorkTaskRelate(Class<? extends WorkTaskRelate> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.child = inits.isInitialized("child") ? new QChild(forProperty("child"), inits.get("child")) : null;
        this.easyTask = inits.isInitialized("easyTask") ? new QEasyTask(forProperty("easyTask")) : null;
        this.task = inits.isInitialized("task") ? new QTask(forProperty("task")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

