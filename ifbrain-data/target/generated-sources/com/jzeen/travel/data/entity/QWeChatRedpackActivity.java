package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QWeChatRedpackActivity is a Querydsl query type for WeChatRedpackActivity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QWeChatRedpackActivity extends EntityPathBase<WeChatRedpackActivity> {

    private static final long serialVersionUID = 2096087221L;

    public static final QWeChatRedpackActivity weChatRedpackActivity = new QWeChatRedpackActivity("weChatRedpackActivity");

    public final StringPath actType = createString("actType");

    public final NumberPath<Integer> clickTime = createNumber("clickTime", Integer.class);

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath openid = createString("openid");

    public final NumberPath<Integer> result = createNumber("result", Integer.class);

    public final NumberPath<Integer> sendFlag = createNumber("sendFlag", Integer.class);

    public final DateTimePath<java.util.Date> takepartinTime = createDateTime("takepartinTime", java.util.Date.class);

    public QWeChatRedpackActivity(String variable) {
        super(WeChatRedpackActivity.class, forVariable(variable));
    }

    public QWeChatRedpackActivity(Path<? extends WeChatRedpackActivity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWeChatRedpackActivity(PathMetadata<?> metadata) {
        super(WeChatRedpackActivity.class, metadata);
    }

}

