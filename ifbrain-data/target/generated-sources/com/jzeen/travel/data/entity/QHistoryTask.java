package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QHistoryTask is a Querydsl query type for HistoryTask
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QHistoryTask extends EntityPathBase<HistoryTask> {

    private static final long serialVersionUID = -1099561925L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHistoryTask historyTask = new QHistoryTask("historyTask");

    public final QChild child;

    public final QEasyTask easyTaskId;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DateTimePath<java.util.Date> startTime = createDateTime("startTime", java.util.Date.class);

    public final StringPath taskName = createString("taskName");

    public final NumberPath<Integer> taskTimes = createNumber("taskTimes", Integer.class);

    public final NumberPath<Integer> timesAdd = createNumber("timesAdd", Integer.class);

    public final QUser user;

    public QHistoryTask(String variable) {
        this(HistoryTask.class, forVariable(variable), INITS);
    }

    public QHistoryTask(Path<? extends HistoryTask> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QHistoryTask(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QHistoryTask(PathMetadata<?> metadata, PathInits inits) {
        this(HistoryTask.class, metadata, inits);
    }

    public QHistoryTask(Class<? extends HistoryTask> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.child = inits.isInitialized("child") ? new QChild(forProperty("child"), inits.get("child")) : null;
        this.easyTaskId = inits.isInitialized("easyTaskId") ? new QEasyTask(forProperty("easyTaskId")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

