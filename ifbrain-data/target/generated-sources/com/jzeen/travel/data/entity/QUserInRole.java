package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QUserInRole is a Querydsl query type for UserInRole
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QUserInRole extends EntityPathBase<UserInRole> {

    private static final long serialVersionUID = -1795517788L;

    public static final QUserInRole userInRole = new QUserInRole("userInRole");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> memberId = createNumber("memberId", Integer.class);

    public final NumberPath<Integer> roleId = createNumber("roleId", Integer.class);

    public QUserInRole(String variable) {
        super(UserInRole.class, forVariable(variable));
    }

    public QUserInRole(Path<? extends UserInRole> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserInRole(PathMetadata<?> metadata) {
        super(UserInRole.class, metadata);
    }

}

