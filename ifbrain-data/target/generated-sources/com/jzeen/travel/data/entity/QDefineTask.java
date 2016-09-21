package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QDefineTask is a Querydsl query type for DefineTask
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDefineTask extends EntityPathBase<DefineTask> {

    private static final long serialVersionUID = 181028254L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDefineTask defineTask = new QDefineTask("defineTask");

    public final QChild child;

    public final QEasyTask easyTaskId;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DateTimePath<java.util.Date> startTime = createDateTime("startTime", java.util.Date.class);

    public final StringPath taskName = createString("taskName");

    public final NumberPath<Integer> taskstatus = createNumber("taskstatus", Integer.class);

    public final NumberPath<Integer> taskTimes = createNumber("taskTimes", Integer.class);

    public final NumberPath<Integer> timesAdd = createNumber("timesAdd", Integer.class);

    public final NumberPath<Integer> tokennumbers = createNumber("tokennumbers", Integer.class);

    public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

    public final QUser user;

    public QDefineTask(String variable) {
        this(DefineTask.class, forVariable(variable), INITS);
    }

    public QDefineTask(Path<? extends DefineTask> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDefineTask(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDefineTask(PathMetadata<?> metadata, PathInits inits) {
        this(DefineTask.class, metadata, inits);
    }

    public QDefineTask(Class<? extends DefineTask> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.child = inits.isInitialized("child") ? new QChild(forProperty("child"), inits.get("child")) : null;
        this.easyTaskId = inits.isInitialized("easyTaskId") ? new QEasyTask(forProperty("easyTaskId")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

