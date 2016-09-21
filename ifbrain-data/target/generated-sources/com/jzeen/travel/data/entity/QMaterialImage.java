package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QMaterialImage is a Querydsl query type for MaterialImage
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMaterialImage extends EntityPathBase<MaterialImage> {

    private static final long serialVersionUID = -259302410L;

    public static final QMaterialImage materialImage = new QMaterialImage("materialImage");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final StringPath fileName = createString("fileName");

    public final StringPath filePath = createString("filePath");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QMaterialImage(String variable) {
        super(MaterialImage.class, forVariable(variable));
    }

    public QMaterialImage(Path<? extends MaterialImage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMaterialImage(PathMetadata<?> metadata) {
        super(MaterialImage.class, metadata);
    }

}

