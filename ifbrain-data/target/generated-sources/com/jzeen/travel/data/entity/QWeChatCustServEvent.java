package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QWeChatCustServEvent is a Querydsl query type for WeChatCustServEvent
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QWeChatCustServEvent extends EntityPathBase<WeChatCustServEvent> {

    private static final long serialVersionUID = -743798931L;

    public static final QWeChatCustServEvent weChatCustServEvent = new QWeChatCustServEvent("weChatCustServEvent");

    public final NumberPath<Integer> clickTime = createNumber("clickTime", Integer.class);

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath openid = createString("openid");

    public QWeChatCustServEvent(String variable) {
        super(WeChatCustServEvent.class, forVariable(variable));
    }

    public QWeChatCustServEvent(Path<? extends WeChatCustServEvent> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWeChatCustServEvent(PathMetadata<?> metadata) {
        super(WeChatCustServEvent.class, metadata);
    }

}

