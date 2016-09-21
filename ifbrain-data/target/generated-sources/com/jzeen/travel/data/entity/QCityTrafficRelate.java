package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QCityTrafficRelate is a Querydsl query type for CityTrafficRelate
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCityTrafficRelate extends EntityPathBase<CityTrafficRelate> {

    private static final long serialVersionUID = 1489043085L;

    public static final QCityTrafficRelate cityTrafficRelate = new QCityTrafficRelate("cityTrafficRelate");

    public final NumberPath<Integer> CityTrafficPk = createNumber("CityTrafficPk", Integer.class);

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> orderPk = createNumber("orderPk", Integer.class);

    public QCityTrafficRelate(String variable) {
        super(CityTrafficRelate.class, forVariable(variable));
    }

    public QCityTrafficRelate(Path<? extends CityTrafficRelate> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCityTrafficRelate(PathMetadata<?> metadata) {
        super(CityTrafficRelate.class, metadata);
    }

}

