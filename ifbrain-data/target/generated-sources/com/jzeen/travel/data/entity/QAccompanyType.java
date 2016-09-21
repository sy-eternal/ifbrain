package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QAccompanyType is a Querydsl query type for AccompanyType
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QAccompanyType extends EntityPathBase<AccompanyType> {

    private static final long serialVersionUID = 1900940151L;

    public static final QAccompanyType accompanyType1 = new QAccompanyType("accompanyType1");

    public final StringPath accompanyType = createString("accompanyType");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> pictureId = createNumber("pictureId", Integer.class);

    public QAccompanyType(String variable) {
        super(AccompanyType.class, forVariable(variable));
    }

    public QAccompanyType(Path<? extends AccompanyType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAccompanyType(PathMetadata<?> metadata) {
        super(AccompanyType.class, metadata);
    }

}

