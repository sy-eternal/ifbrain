package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QWeChatRedpackSent is a Querydsl query type for WeChatRedpackSent
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QWeChatRedpackSent extends EntityPathBase<WeChatRedpackSent> {

    private static final long serialVersionUID = -847355842L;

    public static final QWeChatRedpackSent weChatRedpackSent = new QWeChatRedpackSent("weChatRedpackSent");

    public final StringPath actName = createString("actName");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath mchBillno = createString("mchBillno");

    public final StringPath mchId = createString("mchId");

    public final StringPath reOpenid = createString("reOpenid");

    public final StringPath sendListid = createString("sendListid");

    public final NumberPath<Integer> sendTime = createNumber("sendTime", Integer.class);

    public final NumberPath<Integer> totalAmount = createNumber("totalAmount", Integer.class);

    public QWeChatRedpackSent(String variable) {
        super(WeChatRedpackSent.class, forVariable(variable));
    }

    public QWeChatRedpackSent(Path<? extends WeChatRedpackSent> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWeChatRedpackSent(PathMetadata<?> metadata) {
        super(WeChatRedpackSent.class, metadata);
    }

}

