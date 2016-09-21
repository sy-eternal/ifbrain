package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCourseCode is a Querydsl query type for CourseCode
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCourseCode extends EntityPathBase<CourseCode> {

    private static final long serialVersionUID = -604531610L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCourseCode courseCode = new QCourseCode("courseCode");

    public final DateTimePath<java.util.Date> classTime = createDateTime("classTime", java.util.Date.class);

    public final StringPath courseDescription = createString("courseDescription");

    public final QCourse courseId;

    public final ListPath<FreeFile, QFreeFile> freefile = this.<FreeFile, QFreeFile>createList("freefile", FreeFile.class, QFreeFile.class, PathInits.DIRECT2);

    public final ListPath<FreeVideo, QFreeVideo> freevideo = this.<FreeVideo, QFreeVideo>createList("freevideo", FreeVideo.class, QFreeVideo.class, PathInits.DIRECT2);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath lessonName = createString("lessonName");

    public final NumberPath<Integer> ordinalNumber = createNumber("ordinalNumber", Integer.class);

    public QCourseCode(String variable) {
        this(CourseCode.class, forVariable(variable), INITS);
    }

    public QCourseCode(Path<? extends CourseCode> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCourseCode(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCourseCode(PathMetadata<?> metadata, PathInits inits) {
        this(CourseCode.class, metadata, inits);
    }

    public QCourseCode(Class<? extends CourseCode> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.courseId = inits.isInitialized("courseId") ? new QCourse(forProperty("courseId")) : null;
    }

}

