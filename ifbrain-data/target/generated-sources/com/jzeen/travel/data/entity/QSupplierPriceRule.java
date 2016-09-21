package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSupplierPriceRule is a Querydsl query type for SupplierPriceRule
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSupplierPriceRule extends EntityPathBase<SupplierPriceRule> {

    private static final long serialVersionUID = -1083264517L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSupplierPriceRule supplierPriceRule = new QSupplierPriceRule("supplierPriceRule");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<java.math.BigDecimal> priceCoefficient = createNumber("priceCoefficient", java.math.BigDecimal.class);

    public final QSupplier supplier;

    public final NumberPath<Integer> supplierTypeCode = createNumber("supplierTypeCode", Integer.class);

    public QSupplierPriceRule(String variable) {
        this(SupplierPriceRule.class, forVariable(variable), INITS);
    }

    public QSupplierPriceRule(Path<? extends SupplierPriceRule> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSupplierPriceRule(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSupplierPriceRule(PathMetadata<?> metadata, PathInits inits) {
        this(SupplierPriceRule.class, metadata, inits);
    }

    public QSupplierPriceRule(Class<? extends SupplierPriceRule> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.supplier = inits.isInitialized("supplier") ? new QSupplier(forProperty("supplier")) : null;
    }

}

