package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QMaterialType is a Querydsl query type for MaterialType
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMaterialType extends EntityPathBase<MaterialType> {

    private static final long serialVersionUID = 130522431L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMaterialType materialType = new QMaterialType("materialType");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final ListPath<IfbrainTask, QIfbrainTask> ifbraintask = this.<IfbrainTask, QIfbrainTask>createList("ifbraintask", IfbrainTask.class, QIfbrainTask.class, PathInits.DIRECT2);

    public final QMaterialImage materialimage;

    public final StringPath materialName = createString("materialName");

    public final StringPath shortmaterialName = createString("shortmaterialName");

    public QMaterialType(String variable) {
        this(MaterialType.class, forVariable(variable), INITS);
    }

    public QMaterialType(Path<? extends MaterialType> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMaterialType(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMaterialType(PathMetadata<?> metadata, PathInits inits) {
        this(MaterialType.class, metadata, inits);
    }

    public QMaterialType(Class<? extends MaterialType> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.materialimage = inits.isInitialized("materialimage") ? new QMaterialImage(forProperty("materialimage")) : null;
    }

}

