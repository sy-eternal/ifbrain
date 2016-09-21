package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QInnerCityVehicle is a Querydsl query type for InnerCityVehicle
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QInnerCityVehicle extends EntityPathBase<InnerCityVehicle> {

    private static final long serialVersionUID = 318187593L;

    public static final QInnerCityVehicle innerCityVehicle = new QInnerCityVehicle("innerCityVehicle");

    public final NumberPath<Integer> completeItenary = createNumber("completeItenary", Integer.class);

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> innerCityTraffic = createNumber("innerCityTraffic", Integer.class);

    public final StringPath remark = createString("remark");

    public QInnerCityVehicle(String variable) {
        super(InnerCityVehicle.class, forVariable(variable));
    }

    public QInnerCityVehicle(Path<? extends InnerCityVehicle> path) {
        super(path.getType(), path.getMetadata());
    }

    public QInnerCityVehicle(PathMetadata<?> metadata) {
        super(InnerCityVehicle.class, metadata);
    }

}

