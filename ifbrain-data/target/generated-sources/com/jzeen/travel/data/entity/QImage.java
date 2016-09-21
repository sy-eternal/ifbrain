package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QImage is a Querydsl query type for Image
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QImage extends EntityPathBase<Image> {

    private static final long serialVersionUID = -1659489571L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QImage image = new QImage("image");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final StringPath fileName = createString("fileName");

    public final StringPath filePath = createString("filePath");

    public final ListPath<GuideImageRelate, QGuideImageRelate> guideImageRelate = this.<GuideImageRelate, QGuideImageRelate>createList("guideImageRelate", GuideImageRelate.class, QGuideImageRelate.class, PathInits.DIRECT2);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QTheme theme;

    public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

    public QImage(String variable) {
        this(Image.class, forVariable(variable), INITS);
    }

    public QImage(Path<? extends Image> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QImage(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QImage(PathMetadata<?> metadata, PathInits inits) {
        this(Image.class, metadata, inits);
    }

    public QImage(Class<? extends Image> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.theme = inits.isInitialized("theme") ? new QTheme(forProperty("theme"), inits.get("theme")) : null;
    }

}

