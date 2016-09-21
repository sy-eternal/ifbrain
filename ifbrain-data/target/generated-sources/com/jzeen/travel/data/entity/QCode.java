package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCode is a Querydsl query type for Code
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCode extends EntityPathBase<Code> {

    private static final long serialVersionUID = 916122667L;

    public static final QCode code = new QCode("code");

    public final StringPath classs = createString("classs");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final ListPath<Supplier, QSupplier> supplier = this.<Supplier, QSupplier>createList("supplier", Supplier.class, QSupplier.class, PathInits.DIRECT2);

    public final StringPath type = createString("type");

    public final NumberPath<Integer> value = createNumber("value", Integer.class);

    public QCode(String variable) {
        super(Code.class, forVariable(variable));
    }

    public QCode(Path<? extends Code> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCode(PathMetadata<?> metadata) {
        super(Code.class, metadata);
    }

}

