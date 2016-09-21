package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QExchangeRate is a Querydsl query type for ExchangeRate
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QExchangeRate extends EntityPathBase<ExchangeRate> {

    private static final long serialVersionUID = -619207199L;

    public static final QExchangeRate exchangeRate = new QExchangeRate("exchangeRate");

    public final StringPath buyingcurrency = createString("buyingcurrency");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<java.math.BigDecimal> exchangerate = createNumber("exchangerate", java.math.BigDecimal.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath sellingcurrency = createString("sellingcurrency");

    public QExchangeRate(String variable) {
        super(ExchangeRate.class, forVariable(variable));
    }

    public QExchangeRate(Path<? extends ExchangeRate> path) {
        super(path.getType(), path.getMetadata());
    }

    public QExchangeRate(PathMetadata<?> metadata) {
        super(ExchangeRate.class, metadata);
    }

}

