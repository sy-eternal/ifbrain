package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QActivity is a Querydsl query type for Activity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QActivity extends EntityPathBase<Activity> {

    private static final long serialVersionUID = 718463565L;

    public static final QActivity activity = new QActivity("activity");

    public final StringPath activityAddress = createString("activityAddress");

    public final StringPath activityName = createString("activityName");

    public final ListPath<ActivityOrder, QActivityOrder> activityOrder = this.<ActivityOrder, QActivityOrder>createList("activityOrder", ActivityOrder.class, QActivityOrder.class, PathInits.DIRECT2);

    public final DateTimePath<java.util.Date> activityTime = createDateTime("activityTime", java.util.Date.class);

    public final NumberPath<Integer> activityType = createNumber("activityType", Integer.class);

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<java.math.BigDecimal> listPrice = createNumber("listPrice", java.math.BigDecimal.class);

    public QActivity(String variable) {
        super(Activity.class, forVariable(variable));
    }

    public QActivity(Path<? extends Activity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QActivity(PathMetadata<?> metadata) {
        super(Activity.class, metadata);
    }

}

