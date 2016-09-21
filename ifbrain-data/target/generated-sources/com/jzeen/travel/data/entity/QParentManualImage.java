package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QParentManualImage is a Querydsl query type for ParentManualImage
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QParentManualImage extends EntityPathBase<ParentManualImage> {

    private static final long serialVersionUID = 786793645L;

    public static final QParentManualImage parentManualImage = new QParentManualImage("parentManualImage");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final StringPath fileName = createString("fileName");

    public final StringPath filePath = createString("filePath");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QParentManualImage(String variable) {
        super(ParentManualImage.class, forVariable(variable));
    }

    public QParentManualImage(Path<? extends ParentManualImage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QParentManualImage(PathMetadata<?> metadata) {
        super(ParentManualImage.class, metadata);
    }

}

