package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QVideo is a Querydsl query type for Video
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QVideo extends EntityPathBase<Video> {

    private static final long serialVersionUID = -1647600131L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVideo video = new QVideo("video");

    public final QChild child;

    public final NumberPath<Integer> classId = createNumber("classId", Integer.class);

    public final StringPath courseLevel = createString("courseLevel");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath lessonName = createString("lessonName");

    public final NumberPath<Integer> ordinalNumber = createNumber("ordinalNumber", Integer.class);

    public final StringPath videoPath = createString("videoPath");

    public QVideo(String variable) {
        this(Video.class, forVariable(variable), INITS);
    }

    public QVideo(Path<? extends Video> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVideo(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVideo(PathMetadata<?> metadata, PathInits inits) {
        this(Video.class, metadata, inits);
    }

    public QVideo(Class<? extends Video> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.child = inits.isInitialized("child") ? new QChild(forProperty("child"), inits.get("child")) : null;
    }

}

