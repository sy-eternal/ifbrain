package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QDemoUser is a Querydsl query type for DemoUser
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDemoUser extends EntityPathBase<DemoUser> {

    private static final long serialVersionUID = -1063869556L;

    public static final QDemoUser demoUser = new QDemoUser("demoUser");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath number = createString("number");

    public final NumberPath<Integer> visitCount = createNumber("visitCount", Integer.class);

    public QDemoUser(String variable) {
        super(DemoUser.class, forVariable(variable));
    }

    public QDemoUser(Path<? extends DemoUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDemoUser(PathMetadata<?> metadata) {
        super(DemoUser.class, metadata);
    }

}

