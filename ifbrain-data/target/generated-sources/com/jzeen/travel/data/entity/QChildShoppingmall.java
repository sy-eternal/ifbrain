package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QChildShoppingmall is a Querydsl query type for ChildShoppingmall
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QChildShoppingmall extends EntityPathBase<ChildShoppingmall> {

    private static final long serialVersionUID = -736110278L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChildShoppingmall childShoppingmall = new QChildShoppingmall("childShoppingmall");

    public final QChild child;

    public final StringPath commodityName = createString("commodityName");

    public final StringPath courseLevel = createString("courseLevel");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> ordinalNumber = createNumber("ordinalNumber", Integer.class);

    public final NumberPath<Integer> payStatus = createNumber("payStatus", Integer.class);

    public final NumberPath<java.math.BigDecimal> price = createNumber("price", java.math.BigDecimal.class);

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    public final StringPath result = createString("result");

    public final QShoppingmallCommodity shoppingmallCommodity;

    public final NumberPath<java.math.BigDecimal> sumPrice = createNumber("sumPrice", java.math.BigDecimal.class);

    public final StringPath type = createString("type");

    public QChildShoppingmall(String variable) {
        this(ChildShoppingmall.class, forVariable(variable), INITS);
    }

    public QChildShoppingmall(Path<? extends ChildShoppingmall> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QChildShoppingmall(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QChildShoppingmall(PathMetadata<?> metadata, PathInits inits) {
        this(ChildShoppingmall.class, metadata, inits);
    }

    public QChildShoppingmall(Class<? extends ChildShoppingmall> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.child = inits.isInitialized("child") ? new QChild(forProperty("child"), inits.get("child")) : null;
        this.shoppingmallCommodity = inits.isInitialized("shoppingmallCommodity") ? new QShoppingmallCommodity(forProperty("shoppingmallCommodity"), inits.get("shoppingmallCommodity")) : null;
    }

}

