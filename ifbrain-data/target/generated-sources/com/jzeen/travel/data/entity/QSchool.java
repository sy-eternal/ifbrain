package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSchool is a Querydsl query type for School
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSchool extends EntityPathBase<School> {

    private static final long serialVersionUID = 372703794L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSchool school = new QSchool("school");

    public final QCitySchool citySchool;

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QMember member;

    public final ListPath<SchoolClass, QSchoolClass> schoolClass = this.<SchoolClass, QSchoolClass>createList("schoolClass", SchoolClass.class, QSchoolClass.class, PathInits.DIRECT2);

    public final StringPath scName = createString("scName");

    public QSchool(String variable) {
        this(School.class, forVariable(variable), INITS);
    }

    public QSchool(Path<? extends School> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSchool(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSchool(PathMetadata<?> metadata, PathInits inits) {
        this(School.class, metadata, inits);
    }

    public QSchool(Class<? extends School> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.citySchool = inits.isInitialized("citySchool") ? new QCitySchool(forProperty("citySchool")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

