package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QZDemoUser is a Querydsl query type for ZDemoUser
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QZDemoUser extends EntityPathBase<ZDemoUser> {

    private static final long serialVersionUID = 48101226L;

    public static final QZDemoUser zDemoUser = new QZDemoUser("zDemoUser");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath number = createString("number");

    public final NumberPath<Integer> visitCount = createNumber("visitCount", Integer.class);

    public QZDemoUser(String variable) {
        super(ZDemoUser.class, forVariable(variable));
    }

    public QZDemoUser(Path<? extends ZDemoUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QZDemoUser(PathMetadata<?> metadata) {
        super(ZDemoUser.class, metadata);
    }

}

