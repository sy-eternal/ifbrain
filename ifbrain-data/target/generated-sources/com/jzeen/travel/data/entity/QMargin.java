package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QMargin is a Querydsl query type for Margin
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMargin extends EntityPathBase<Margin> {

    private static final long serialVersionUID = 199371884L;

    public static final QMargin margin = new QMargin("margin");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<java.math.BigDecimal> marginprice = createNumber("marginprice", java.math.BigDecimal.class);

    public final StringPath margintype = createString("margintype");

    public QMargin(String variable) {
        super(Margin.class, forVariable(variable));
    }

    public QMargin(Path<? extends Margin> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMargin(PathMetadata<?> metadata) {
        super(Margin.class, metadata);
    }

}

