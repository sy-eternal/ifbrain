package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QWeChatUser is a Querydsl query type for WeChatUser
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QWeChatUser extends EntityPathBase<WeChatUser> {

    private static final long serialVersionUID = -1372363793L;

    public static final QWeChatUser weChatUser = new QWeChatUser("weChatUser");

    public final NumberPath<Integer> cancel_time = createNumber("cancel_time", Integer.class);

    public final StringPath city = createString("city");

    public final StringPath country = createString("country");

    public final DateTimePath<java.util.Date> create_time = createDateTime("create_time", java.util.Date.class);

    public final NumberPath<Integer> first_subscribe_time = createNumber("first_subscribe_time", Integer.class);

    public final NumberPath<Integer> groupid = createNumber("groupid", Integer.class);

    public final StringPath headimgurl = createString("headimgurl");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath language = createString("language");

    public final StringPath nickname = createString("nickname");

    public final StringPath openid = createString("openid");

    public final StringPath province = createString("province");

    public final StringPath remark = createString("remark");

    public final NumberPath<Integer> sex = createNumber("sex", Integer.class);

    public final NumberPath<Integer> subscribe = createNumber("subscribe", Integer.class);

    public final NumberPath<Integer> subscribe_time = createNumber("subscribe_time", Integer.class);

    public final StringPath unionid = createString("unionid");

    public final NumberPath<Integer> userId = createNumber("userId", Integer.class);

    public QWeChatUser(String variable) {
        super(WeChatUser.class, forVariable(variable));
    }

    public QWeChatUser(Path<? extends WeChatUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWeChatUser(PathMetadata<?> metadata) {
        super(WeChatUser.class, metadata);
    }

}

