package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSupplierPriceRuleActive is a Querydsl query type for SupplierPriceRuleActive
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSupplierPriceRuleActive extends EntityPathBase<SupplierPriceRuleActive> {

    private static final long serialVersionUID = 1983280353L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSupplierPriceRuleActive supplierPriceRuleActive = new QSupplierPriceRuleActive("supplierPriceRuleActive");

    public final QCode code;

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath priceCoefficient = createString("priceCoefficient");

    public final QSupplier supplier;

    public QSupplierPriceRuleActive(String variable) {
        this(SupplierPriceRuleActive.class, forVariable(variable), INITS);
    }

    public QSupplierPriceRuleActive(Path<? extends SupplierPriceRuleActive> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSupplierPriceRuleActive(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSupplierPriceRuleActive(PathMetadata<?> metadata, PathInits inits) {
        this(SupplierPriceRuleActive.class, metadata, inits);
    }

    public QSupplierPriceRuleActive(Class<? extends SupplierPriceRuleActive> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.code = inits.isInitialized("code") ? new QCode(forProperty("code")) : null;
        this.supplier = inits.isInitialized("supplier") ? new QSupplier(forProperty("supplier")) : null;
    }

}

