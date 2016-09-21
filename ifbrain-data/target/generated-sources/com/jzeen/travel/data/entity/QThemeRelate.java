package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QThemeRelate is a Querydsl query type for ThemeRelate
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QThemeRelate extends EntityPathBase<ThemeRelate> {

    private static final long serialVersionUID = -1990830460L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QThemeRelate themeRelate = new QThemeRelate("themeRelate");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final StringPath defineTheme = createString("defineTheme");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QOrder order;

    public final QTheme theme;

    public final StringPath themeName = createString("themeName");

    public QThemeRelate(String variable) {
        this(ThemeRelate.class, forVariable(variable), INITS);
    }

    public QThemeRelate(Path<? extends ThemeRelate> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QThemeRelate(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QThemeRelate(PathMetadata<?> metadata, PathInits inits) {
        this(ThemeRelate.class, metadata, inits);
    }

    public QThemeRelate(Class<? extends ThemeRelate> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.order = inits.isInitialized("order") ? new QOrder(forProperty("order"), inits.get("order")) : null;
        this.theme = inits.isInitialized("theme") ? new QTheme(forProperty("theme"), inits.get("theme")) : null;
    }

}

