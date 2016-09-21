package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QApplicationInfo is a Querydsl query type for ApplicationInfo
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QApplicationInfo extends EntityPathBase<ApplicationInfo> {

    private static final long serialVersionUID = 1724564768L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QApplicationInfo applicationInfo = new QApplicationInfo("applicationInfo");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final QDocument document;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final QVisaOrder visaOrder;

    public QApplicationInfo(String variable) {
        this(ApplicationInfo.class, forVariable(variable), INITS);
    }

    public QApplicationInfo(Path<? extends ApplicationInfo> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QApplicationInfo(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QApplicationInfo(PathMetadata<?> metadata, PathInits inits) {
        this(ApplicationInfo.class, metadata, inits);
    }

    public QApplicationInfo(Class<? extends ApplicationInfo> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.document = inits.isInitialized("document") ? new QDocument(forProperty("document")) : null;
        this.visaOrder = inits.isInitialized("visaOrder") ? new QVisaOrder(forProperty("visaOrder"), inits.get("visaOrder")) : null;
    }

}

