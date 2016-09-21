package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QNavigationBar is a Querydsl query type for NavigationBar
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QNavigationBar extends EntityPathBase<NavigationBar> {

    private static final long serialVersionUID = 638761185L;

    public static final QNavigationBar navigationBar = new QNavigationBar("navigationBar");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> type = createNumber("type", Integer.class);

    public QNavigationBar(String variable) {
        super(NavigationBar.class, forVariable(variable));
    }

    public QNavigationBar(Path<? extends NavigationBar> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNavigationBar(PathMetadata<?> metadata) {
        super(NavigationBar.class, metadata);
    }

}

