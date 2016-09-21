package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QMoney is a Querydsl query type for Money
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMoney extends EntityPathBase<Money> {

    private static final long serialVersionUID = -1655723454L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMoney money = new QMoney("money");

    public final NumberPath<java.math.BigDecimal> balance = createNumber("balance", java.math.BigDecimal.class);

    public final QChild childId;

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QMoney(String variable) {
        this(Money.class, forVariable(variable), INITS);
    }

    public QMoney(Path<? extends Money> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMoney(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMoney(PathMetadata<?> metadata, PathInits inits) {
        this(Money.class, metadata, inits);
    }

    public QMoney(Class<? extends Money> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.childId = inits.isInitialized("childId") ? new QChild(forProperty("childId"), inits.get("childId")) : null;
    }

}

