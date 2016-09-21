package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QHeadPortrait is a Querydsl query type for HeadPortrait
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QHeadPortrait extends EntityPathBase<HeadPortrait> {

    private static final long serialVersionUID = 237514201L;

    public static final QHeadPortrait headPortrait = new QHeadPortrait("headPortrait");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final StringPath fileName = createString("fileName");

    public final StringPath filePath = createString("filePath");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QHeadPortrait(String variable) {
        super(HeadPortrait.class, forVariable(variable));
    }

    public QHeadPortrait(Path<? extends HeadPortrait> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHeadPortrait(PathMetadata<?> metadata) {
        super(HeadPortrait.class, metadata);
    }

}

