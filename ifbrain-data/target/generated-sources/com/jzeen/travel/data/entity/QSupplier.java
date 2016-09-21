package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSupplier is a Querydsl query type for Supplier
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSupplier extends EntityPathBase<Supplier> {

    private static final long serialVersionUID = 711125258L;

    public static final QSupplier supplier = new QSupplier("supplier");

    public final StringPath cnName = createString("cnName");

    public final ListPath<Code, QCode> code = this.<Code, QCode>createList("code", Code.class, QCode.class, PathInits.DIRECT2);

    public final StringPath contact = createString("contact");

    public final StringPath contactNotes = createString("contactNotes");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final StringPath enName = createString("enName");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath resEmail = createString("resEmail");

    public final StringPath resPhone = createString("resPhone");

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public final ListPath<SupplierPriceRuleActive, QSupplierPriceRuleActive> supplierpriceruleactive = this.<SupplierPriceRuleActive, QSupplierPriceRuleActive>createList("supplierpriceruleactive", SupplierPriceRuleActive.class, QSupplierPriceRuleActive.class, PathInits.DIRECT2);

    public final NumberPath<Integer> supplierStatus = createNumber("supplierStatus", Integer.class);

    public final StringPath website = createString("website");

    public QSupplier(String variable) {
        super(Supplier.class, forVariable(variable));
    }

    public QSupplier(Path<? extends Supplier> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSupplier(PathMetadata<?> metadata) {
        super(Supplier.class, metadata);
    }

}

