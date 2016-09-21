package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QVisaPrice is a Querydsl query type for VisaPrice
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QVisaPrice extends EntityPathBase<VisaPrice> {

    private static final long serialVersionUID = -2083717622L;

    public static final QVisaPrice visaPrice = new QVisaPrice("visaPrice");

    public final DateTimePath<java.util.Date> createtime = createDateTime("createtime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<java.math.BigDecimal> price = createNumber("price", java.math.BigDecimal.class);

    public final StringPath type = createString("type");

    public final DateTimePath<java.util.Date> updatetime = createDateTime("updatetime", java.util.Date.class);

    public QVisaPrice(String variable) {
        super(VisaPrice.class, forVariable(variable));
    }

    public QVisaPrice(Path<? extends VisaPrice> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVisaPrice(PathMetadata<?> metadata) {
        super(VisaPrice.class, metadata);
    }

}

