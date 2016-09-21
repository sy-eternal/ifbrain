package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSchoolClass is a Querydsl query type for SchoolClass
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSchoolClass extends EntityPathBase<SchoolClass> {

    private static final long serialVersionUID = -149993882L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSchoolClass schoolClass = new QSchoolClass("schoolClass");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QMember member;

    public final StringPath name = createString("name");

    public final QSchool school;

    public final ListPath<Student, QStudent> student = this.<Student, QStudent>createList("student", Student.class, QStudent.class, PathInits.DIRECT2);

    public QSchoolClass(String variable) {
        this(SchoolClass.class, forVariable(variable), INITS);
    }

    public QSchoolClass(Path<? extends SchoolClass> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSchoolClass(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSchoolClass(PathMetadata<?> metadata, PathInits inits) {
        this(SchoolClass.class, metadata, inits);
    }

    public QSchoolClass(Class<? extends SchoolClass> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
        this.school = inits.isInitialized("school") ? new QSchool(forProperty("school"), inits.get("school")) : null;
    }

}

