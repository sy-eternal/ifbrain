package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QTask is a Querydsl query type for Task
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTask extends EntityPathBase<Task> {

    private static final long serialVersionUID = 916616131L;

    public static final QTask task = new QTask("task");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final DateTimePath<java.util.Date> startTime = createDateTime("startTime", java.util.Date.class);

    public final ListPath<WorkTaskRelate, QWorkTaskRelate> workTask = this.<WorkTaskRelate, QWorkTaskRelate>createList("workTask", WorkTaskRelate.class, QWorkTaskRelate.class, PathInits.DIRECT2);

    public QTask(String variable) {
        super(Task.class, forVariable(variable));
    }

    public QTask(Path<? extends Task> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTask(PathMetadata<?> metadata) {
        super(Task.class, metadata);
    }

}

