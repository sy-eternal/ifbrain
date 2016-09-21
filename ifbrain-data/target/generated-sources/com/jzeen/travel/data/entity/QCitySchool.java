package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QCitySchool is a Querydsl query type for CitySchool
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCitySchool extends EntityPathBase<CitySchool> {

    private static final long serialVersionUID = 908131453L;

    public static final QCitySchool citySchool = new QCitySchool("citySchool");

    public final StringPath cityName = createString("cityName");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QCitySchool(String variable) {
        super(CitySchool.class, forVariable(variable));
    }

    public QCitySchool(Path<? extends CitySchool> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCitySchool(PathMetadata<?> metadata) {
        super(CitySchool.class, metadata);
    }

}

