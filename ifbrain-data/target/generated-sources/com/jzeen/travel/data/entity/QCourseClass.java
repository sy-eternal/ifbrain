package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCourseClass is a Querydsl query type for CourseClass
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCourseClass extends EntityPathBase<CourseClass> {

    private static final long serialVersionUID = -1560702433L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCourseClass courseClass = new QCourseClass("courseClass");

    public final ListPath<Child, QChild> child = this.<Child, QChild>createList("child", Child.class, QChild.class, PathInits.DIRECT2);

    public final NumberPath<Integer> childNumber = createNumber("childNumber", Integer.class);

    public final StringPath className = createString("className");

    public final QCourse courseId;

    public final StringPath courseLevel = createString("courseLevel");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> endDate = createDateTime("endDate", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DateTimePath<java.util.Date> startDate = createDateTime("startDate", java.util.Date.class);

    public QCourseClass(String variable) {
        this(CourseClass.class, forVariable(variable), INITS);
    }

    public QCourseClass(Path<? extends CourseClass> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCourseClass(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCourseClass(PathMetadata<?> metadata, PathInits inits) {
        this(CourseClass.class, metadata, inits);
    }

    public QCourseClass(Class<? extends CourseClass> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.courseId = inits.isInitialized("courseId") ? new QCourse(forProperty("courseId")) : null;
    }

}

