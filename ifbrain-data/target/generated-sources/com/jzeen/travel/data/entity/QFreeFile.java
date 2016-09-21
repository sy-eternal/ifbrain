package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QFreeFile is a Querydsl query type for FreeFile
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QFreeFile extends EntityPathBase<FreeFile> {

    private static final long serialVersionUID = 835672390L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFreeFile freeFile = new QFreeFile("freeFile");

    public final QCourseCode courseCode;

    public final QCourse courseLevel;

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final StringPath fileName = createString("fileName");

    public final StringPath filePath = createString("filePath");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QFreeFile(String variable) {
        this(FreeFile.class, forVariable(variable), INITS);
    }

    public QFreeFile(Path<? extends FreeFile> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QFreeFile(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QFreeFile(PathMetadata<?> metadata, PathInits inits) {
        this(FreeFile.class, metadata, inits);
    }

    public QFreeFile(Class<? extends FreeFile> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.courseCode = inits.isInitialized("courseCode") ? new QCourseCode(forProperty("courseCode"), inits.get("courseCode")) : null;
        this.courseLevel = inits.isInitialized("courseLevel") ? new QCourse(forProperty("courseLevel")) : null;
    }

}

