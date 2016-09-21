package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QFreeVideo is a Querydsl query type for FreeVideo
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QFreeVideo extends EntityPathBase<FreeVideo> {

    private static final long serialVersionUID = 150809073L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFreeVideo freeVideo = new QFreeVideo("freeVideo");

    public final QCourseCode courseCode;

    public final QCourse courseLevel;

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QMaterialType materialType;

    public final StringPath videoCName = createString("videoCName");

    public final StringPath videoName = createString("videoName");

    public final StringPath videoPath = createString("videoPath");

    public QFreeVideo(String variable) {
        this(FreeVideo.class, forVariable(variable), INITS);
    }

    public QFreeVideo(Path<? extends FreeVideo> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QFreeVideo(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QFreeVideo(PathMetadata<?> metadata, PathInits inits) {
        this(FreeVideo.class, metadata, inits);
    }

    public QFreeVideo(Class<? extends FreeVideo> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.courseCode = inits.isInitialized("courseCode") ? new QCourseCode(forProperty("courseCode"), inits.get("courseCode")) : null;
        this.courseLevel = inits.isInitialized("courseLevel") ? new QCourse(forProperty("courseLevel")) : null;
        this.materialType = inits.isInitialized("materialType") ? new QMaterialType(forProperty("materialType"), inits.get("materialType")) : null;
    }

}

