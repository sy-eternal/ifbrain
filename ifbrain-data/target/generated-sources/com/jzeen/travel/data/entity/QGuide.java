package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QGuide is a Querydsl query type for Guide
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QGuide extends EntityPathBase<Guide> {

    private static final long serialVersionUID = -1661090690L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGuide guide = new QGuide("guide");

    public final StringPath accountname = createString("accountname");

    public final StringPath accountnum = createString("accountnum");

    public final StringPath address = createString("address");

    public final StringPath alipayaccount = createString("alipayaccount");

    public final NumberPath<Integer> approvalstatus = createNumber("approvalstatus", Integer.class);

    public final StringPath bankaddress = createString("bankaddress");

    public final StringPath bankname = createString("bankname");

    public final StringPath banktel = createString("banktel");

    public final DateTimePath<java.util.Date> birthday = createDateTime("birthday", java.util.Date.class);

    public final StringPath citydescription = createString("citydescription");

    public final DateTimePath<java.util.Date> createtime = createDateTime("createtime", java.util.Date.class);

    public final StringPath domesticroutingnum = createString("domesticroutingnum");

    public final StringPath drivelicensecode = createString("drivelicensecode");

    public final QExcursionGuide excursionGuide;

    public final NumberPath<Integer> excursiontype = createNumber("excursiontype", Integer.class);

    public final QGuideCar guideCar;

    public final ListPath<GuideComments, QGuideComments> guideComments = this.<GuideComments, QGuideComments>createList("guideComments", GuideComments.class, QGuideComments.class, PathInits.DIRECT2);

    public final ListPath<GuideImageRelate, QGuideImageRelate> guideImageRelate = this.<GuideImageRelate, QGuideImageRelate>createList("guideImageRelate", GuideImageRelate.class, QGuideImageRelate.class, PathInits.DIRECT2);

    public final ListPath<GuideTags, QGuideTags> guidetag = this.<GuideTags, QGuideTags>createList("guidetag", GuideTags.class, QGuideTags.class, PathInits.DIRECT2);

    public final StringPath has_car = createString("has_car");

    public final QCity hostCity;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath idcardnumber = createString("idcardnumber");

    public final StringPath internationalswiftnum = createString("internationalswiftnum");

    public final StringPath motto = createString("motto");

    public final StringPath nationality = createString("nationality");

    public final StringPath passportcode = createString("passportcode");

    public final StringPath payalaccount = createString("payalaccount");

    public final NumberPath<Integer> paymethodcode = createNumber("paymethodcode", Integer.class);

    public final StringPath selfdescription = createString("selfdescription");

    public final NumberPath<Integer> sex = createNumber("sex", Integer.class);

    public final QStandardGuide standardGuide;

    public final NumberPath<Integer> stayduration = createNumber("stayduration", Integer.class);

    public final NumberPath<Integer> toothercity = createNumber("toothercity", Integer.class);

    public final QUser user;

    public QGuide(String variable) {
        this(Guide.class, forVariable(variable), INITS);
    }

    public QGuide(Path<? extends Guide> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QGuide(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QGuide(PathMetadata<?> metadata, PathInits inits) {
        this(Guide.class, metadata, inits);
    }

    public QGuide(Class<? extends Guide> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.excursionGuide = inits.isInitialized("excursionGuide") ? new QExcursionGuide(forProperty("excursionGuide"), inits.get("excursionGuide")) : null;
        this.guideCar = inits.isInitialized("guideCar") ? new QGuideCar(forProperty("guideCar"), inits.get("guideCar")) : null;
        this.hostCity = inits.isInitialized("hostCity") ? new QCity(forProperty("hostCity"), inits.get("hostCity")) : null;
        this.standardGuide = inits.isInitialized("standardGuide") ? new QStandardGuide(forProperty("standardGuide"), inits.get("standardGuide")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

