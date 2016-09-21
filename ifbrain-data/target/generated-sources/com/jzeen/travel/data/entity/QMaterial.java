package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QMaterial is a Querydsl query type for Material
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMaterial extends EntityPathBase<Material> {

    private static final long serialVersionUID = -1621470107L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMaterial material = new QMaterial("material");

    public final StringPath author = createString("author");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final SimplePath<java.sql.Blob> materialContent = createSimple("materialContent", java.sql.Blob.class);

    public final QMaterialImage materialimage;

    public final StringPath materialName = createString("materialName");

    public final QMaterialType materialType;

    public final NumberPath<Integer> visitornumber = createNumber("visitornumber", Integer.class);

    public QMaterial(String variable) {
        this(Material.class, forVariable(variable), INITS);
    }

    public QMaterial(Path<? extends Material> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMaterial(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMaterial(PathMetadata<?> metadata, PathInits inits) {
        this(Material.class, metadata, inits);
    }

    public QMaterial(Class<? extends Material> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.materialimage = inits.isInitialized("materialimage") ? new QMaterialImage(forProperty("materialimage")) : null;
        this.materialType = inits.isInitialized("materialType") ? new QMaterialType(forProperty("materialType"), inits.get("materialType")) : null;
    }

}

