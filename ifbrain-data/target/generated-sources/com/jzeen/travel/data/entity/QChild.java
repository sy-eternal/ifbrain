package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QChild is a Querydsl query type for Child
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QChild extends EntityPathBase<Child> {

    private static final long serialVersionUID = -1665171810L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChild child = new QChild("child");

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final NumberPath<java.math.BigDecimal> balance = createNumber("balance", java.math.BigDecimal.class);

    public final DateTimePath<java.util.Date> birth = createDateTime("birth", java.util.Date.class);

    public final ListPath<ChildShoppingmall, QChildShoppingmall> childShoppingmall = this.<ChildShoppingmall, QChildShoppingmall>createList("childShoppingmall", ChildShoppingmall.class, QChildShoppingmall.class, PathInits.DIRECT2);

    public final QCourseClass courseClass;

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final ListPath<DefineTask, QDefineTask> defineTask = this.<DefineTask, QDefineTask>createList("defineTask", DefineTask.class, QDefineTask.class, PathInits.DIRECT2);

    public final DateTimePath<java.util.Date> editTime = createDateTime("editTime", java.util.Date.class);

    public final NumberPath<Integer> gender = createNumber("gender", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final ListPath<IfbrainIndex, QIfbrainIndex> ifbrainIndex = this.<IfbrainIndex, QIfbrainIndex>createList("ifbrainIndex", IfbrainIndex.class, QIfbrainIndex.class, PathInits.DIRECT2);

    public final QImage image;

    public final QMoney money;

    public final StringPath name = createString("name");

    public final StringPath nickName = createString("nickName");

    public final StringPath type = createString("type");

    public final QUser user;

    public final ListPath<Video, QVideo> video = this.<Video, QVideo>createList("video", Video.class, QVideo.class, PathInits.DIRECT2);

    public QChild(String variable) {
        this(Child.class, forVariable(variable), INITS);
    }

    public QChild(Path<? extends Child> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QChild(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QChild(PathMetadata<?> metadata, PathInits inits) {
        this(Child.class, metadata, inits);
    }

    public QChild(Class<? extends Child> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.courseClass = inits.isInitialized("courseClass") ? new QCourseClass(forProperty("courseClass"), inits.get("courseClass")) : null;
        this.image = inits.isInitialized("image") ? new QImage(forProperty("image"), inits.get("image")) : null;
        this.money = inits.isInitialized("money") ? new QMoney(forProperty("money"), inits.get("money")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

