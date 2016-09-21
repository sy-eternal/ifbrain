package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QEasyTask is a Querydsl query type for EasyTask
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QEasyTask extends EntityPathBase<EasyTask> {

    private static final long serialVersionUID = 1604856805L;

    public static final QEasyTask easyTask = new QEasyTask("easyTask");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final ListPath<WorkTaskRelate, QWorkTaskRelate> workTaskRelate = this.<WorkTaskRelate, QWorkTaskRelate>createList("workTaskRelate", WorkTaskRelate.class, QWorkTaskRelate.class, PathInits.DIRECT2);

    public QEasyTask(String variable) {
        super(EasyTask.class, forVariable(variable));
    }

    public QEasyTask(Path<? extends EasyTask> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEasyTask(PathMetadata<?> metadata) {
        super(EasyTask.class, metadata);
    }

}

