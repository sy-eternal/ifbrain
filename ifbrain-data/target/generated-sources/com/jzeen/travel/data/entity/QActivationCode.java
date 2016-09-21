package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QActivationCode is a Querydsl query type for ActivationCode
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QActivationCode extends EntityPathBase<ActivationCode> {

    private static final long serialVersionUID = 1106391265L;

    public static final QActivationCode activationCode = new QActivationCode("activationCode");

    public final StringPath code = createString("code");

    public final DateTimePath<java.util.Date> createtime = createDateTime("createtime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath identifier = createString("identifier");

    public final StringPath phone = createString("phone");

    public final DateTimePath<java.util.Date> sendtime = createDateTime("sendtime", java.util.Date.class);

    public final StringPath type = createString("type");

    public final NumberPath<Integer> userId = createNumber("userId", Integer.class);

    public final DateTimePath<java.util.Date> validation = createDateTime("validation", java.util.Date.class);

    public QActivationCode(String variable) {
        super(ActivationCode.class, forVariable(variable));
    }

    public QActivationCode(Path<? extends ActivationCode> path) {
        super(path.getType(), path.getMetadata());
    }

    public QActivationCode(PathMetadata<?> metadata) {
        super(ActivationCode.class, metadata);
    }

}

