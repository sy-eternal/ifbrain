package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QThemeActive is a Querydsl query type for ThemeActive
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QThemeActive extends EntityPathBase<ThemeActive> {

    private static final long serialVersionUID = 1815840305L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QThemeActive themeActive = new QThemeActive("themeActive");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QImage image;

    public final ListPath<SpotThemeRelate, QSpotThemeRelate> spotRelates = this.<SpotThemeRelate, QSpotThemeRelate>createList("spotRelates", SpotThemeRelate.class, QSpotThemeRelate.class, PathInits.DIRECT2);

    public final StringPath theme = createString("theme");

    public QThemeActive(String variable) {
        this(ThemeActive.class, forVariable(variable), INITS);
    }

    public QThemeActive(Path<? extends ThemeActive> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QThemeActive(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QThemeActive(PathMetadata<?> metadata, PathInits inits) {
        this(ThemeActive.class, metadata, inits);
    }

    public QThemeActive(Class<? extends ThemeActive> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.image = inits.isInitialized("image") ? new QImage(forProperty("image"), inits.get("image")) : null;
    }

}

