package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 916662793L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser user = new QUser("user");

    public final NumberPath<Integer> activeStatus = createNumber("activeStatus", Integer.class);

    public final StringPath activitycode = createString("activitycode");

    public final DateTimePath<java.util.Date> activityValidity = createDateTime("activityValidity", java.util.Date.class);

    public final ListPath<Child, QChild> child = this.<Child, QChild>createList("child", Child.class, QChild.class, PathInits.DIRECT2);

    public final StringPath code = createString("code");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final StringPath email = createString("email");

    public final StringPath firstName = createString("firstName");

    public final QGuide guide;

    public final ListPath<GuideOrder, QGuideOrder> guideOrder = this.<GuideOrder, QGuideOrder>createList("guideOrder", GuideOrder.class, QGuideOrder.class, PathInits.DIRECT2);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath identifier = createString("identifier");

    public final QImage image;

    public final StringPath lastName = createString("lastName");

    public final StringPath mobile = createString("mobile");

    public final StringPath password = createString("password");

    public final DateTimePath<java.util.Date> sendtime = createDateTime("sendtime", java.util.Date.class);

    public final NumberPath<Integer> userType = createNumber("userType", Integer.class);

    public final DateTimePath<java.util.Date> validation = createDateTime("validation", java.util.Date.class);

    public final StringPath wechat = createString("wechat");

    public final ListPath<WorkTaskRelate, QWorkTaskRelate> workTask = this.<WorkTaskRelate, QWorkTaskRelate>createList("workTask", WorkTaskRelate.class, QWorkTaskRelate.class, PathInits.DIRECT2);

    public QUser(String variable) {
        this(User.class, forVariable(variable), INITS);
    }

    public QUser(Path<? extends User> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUser(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUser(PathMetadata<?> metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUser(Class<? extends User> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.guide = inits.isInitialized("guide") ? new QGuide(forProperty("guide"), inits.get("guide")) : null;
        this.image = inits.isInitialized("image") ? new QImage(forProperty("image"), inits.get("image")) : null;
    }

}

