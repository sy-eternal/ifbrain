package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 202912088L;

    public static final QMember member = new QMember("member1");

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final StringPath description = createString("description");

    public final StringPath email = createString("email");

    public final StringPath firstName = createString("firstName");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath lastName = createString("lastName");

    public final StringPath password = createString("password");

    public final ListPath<Roles, QRoles> roles = this.<Roles, QRoles>createList("roles", Roles.class, QRoles.class, PathInits.DIRECT2);

    public final NumberPath<Integer> sex = createNumber("sex", Integer.class);

    public final StringPath telephone = createString("telephone");

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata<?> metadata) {
        super(Member.class, metadata);
    }

}

