package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QSupplierActive is a Querydsl query type for SupplierActive
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSupplierActive extends EntityPathBase<SupplierActive> {

    private static final long serialVersionUID = 1784865968L;

    public static final QSupplierActive supplierActive = new QSupplierActive("supplierActive");

    public final StringPath country = createString("country");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QSupplierActive(String variable) {
        super(SupplierActive.class, forVariable(variable));
    }

    public QSupplierActive(Path<? extends SupplierActive> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSupplierActive(PathMetadata<?> metadata) {
        super(SupplierActive.class, metadata);
    }

}

