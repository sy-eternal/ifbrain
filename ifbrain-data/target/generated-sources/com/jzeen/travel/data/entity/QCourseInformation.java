package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCourseInformation is a Querydsl query type for CourseInformation
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCourseInformation extends EntityPathBase<CourseInformation> {

    private static final long serialVersionUID = -1337129837L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCourseInformation courseInformation = new QCourseInformation("courseInformation");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final StringPath description = createString("description");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath lessonName = createString("lessonName");

    public final NumberPath<Integer> ordinalNumber = createNumber("ordinalNumber", Integer.class);

    public final QSchoolClass schoolClass;

    public QCourseInformation(String variable) {
        this(CourseInformation.class, forVariable(variable), INITS);
    }

    public QCourseInformation(Path<? extends CourseInformation> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCourseInformation(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCourseInformation(PathMetadata<?> metadata, PathInits inits) {
        this(CourseInformation.class, metadata, inits);
    }

    public QCourseInformation(Class<? extends CourseInformation> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.schoolClass = inits.isInitialized("schoolClass") ? new QSchoolClass(forProperty("schoolClass"), inits.get("schoolClass")) : null;
    }

}

