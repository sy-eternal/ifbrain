package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QDiscount is a Querydsl query type for Discount
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDiscount extends EntityPathBase<Discount> {

    private static final long serialVersionUID = -1647352705L;

    public static final QDiscount discount = new QDiscount("discount");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final StringPath discountCode = createString("discountCode");

    public final NumberPath<java.math.BigDecimal> discountPrice = createNumber("discountPrice", java.math.BigDecimal.class);

    public final NumberPath<Integer> discountStatus = createNumber("discountStatus", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QDiscount(String variable) {
        super(Discount.class, forVariable(variable));
    }

    public QDiscount(Path<? extends Discount> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDiscount(PathMetadata<?> metadata) {
        super(Discount.class, metadata);
    }

}

