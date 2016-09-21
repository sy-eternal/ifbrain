package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QDemandLevel is a Querydsl query type for DemandLevel
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDemandLevel extends EntityPathBase<DemandLevel> {

    private static final long serialVersionUID = 456153083L;

    public static final QDemandLevel demandLevel = new QDemandLevel("demandLevel");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final StringPath demandName = createString("demandName");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QDemandLevel(String variable) {
        super(DemandLevel.class, forVariable(variable));
    }

    public QDemandLevel(Path<? extends DemandLevel> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDemandLevel(PathMetadata<?> metadata) {
        super(DemandLevel.class, metadata);
    }

}

