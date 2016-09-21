package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPurpose is a Querydsl query type for Purpose
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPurpose extends EntityPathBase<Purpose> {

    private static final long serialVersionUID = 825962016L;

    public static final QPurpose purpose1 = new QPurpose("purpose1");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> pictureId = createNumber("pictureId", Integer.class);

    public final StringPath purpose = createString("purpose");

    public QPurpose(String variable) {
        super(Purpose.class, forVariable(variable));
    }

    public QPurpose(Path<? extends Purpose> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPurpose(PathMetadata<?> metadata) {
        super(Purpose.class, metadata);
    }

}

