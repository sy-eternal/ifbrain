package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QIfbrainTask is a Querydsl query type for IfbrainTask
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QIfbrainTask extends EntityPathBase<IfbrainTask> {

    private static final long serialVersionUID = -643642080L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QIfbrainTask ifbrainTask = new QIfbrainTask("ifbrainTask");

    public final QChild child;

    public final QCourseCode courseCode;

    public final QCourse courseLevel;

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final StringPath description = createString("description");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath ifbrainTaskName = createString("ifbrainTaskName");

    public final StringPath ifbrainTaskPath = createString("ifbrainTaskPath");

    public final QMaterialType materialType;

    public final StringPath type = createString("type");

    public QIfbrainTask(String variable) {
        this(IfbrainTask.class, forVariable(variable), INITS);
    }

    public QIfbrainTask(Path<? extends IfbrainTask> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QIfbrainTask(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QIfbrainTask(PathMetadata<?> metadata, PathInits inits) {
        this(IfbrainTask.class, metadata, inits);
    }

    public QIfbrainTask(Class<? extends IfbrainTask> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.child = inits.isInitialized("child") ? new QChild(forProperty("child"), inits.get("child")) : null;
        this.courseCode = inits.isInitialized("courseCode") ? new QCourseCode(forProperty("courseCode"), inits.get("courseCode")) : null;
        this.courseLevel = inits.isInitialized("courseLevel") ? new QCourse(forProperty("courseLevel")) : null;
        this.materialType = inits.isInitialized("materialType") ? new QMaterialType(forProperty("materialType"), inits.get("materialType")) : null;
    }

}

