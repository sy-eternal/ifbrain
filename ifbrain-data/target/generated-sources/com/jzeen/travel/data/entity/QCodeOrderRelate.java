package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QCodeOrderRelate is a Querydsl query type for CodeOrderRelate
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCodeOrderRelate extends EntityPathBase<CodeOrderRelate> {

    private static final long serialVersionUID = 1451201308L;

    public static final QCodeOrderRelate codeOrderRelate = new QCodeOrderRelate("codeOrderRelate");

    public final NumberPath<Integer> codePk = createNumber("codePk", Integer.class);

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> orderPk = createNumber("orderPk", Integer.class);

    public QCodeOrderRelate(String variable) {
        super(CodeOrderRelate.class, forVariable(variable));
    }

    public QCodeOrderRelate(Path<? extends CodeOrderRelate> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCodeOrderRelate(PathMetadata<?> metadata) {
        super(CodeOrderRelate.class, metadata);
    }

}

