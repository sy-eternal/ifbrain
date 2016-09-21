package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QConfiremedTraveler is a Querydsl query type for ConfiremedTraveler
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QConfiremedTraveler extends EntityPathBase<ConfiremedTraveler> {

    private static final long serialVersionUID = 227564345L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QConfiremedTraveler confiremedTraveler = new QConfiremedTraveler("confiremedTraveler");

    public final DateTimePath<java.util.Date> birthDate = createDateTime("birthDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final StringPath driveLicenseCode = createString("driveLicenseCode");

    public final StringPath email = createString("email");

    public final StringPath firstName = createString("firstName");

    public final NumberPath<Integer> gender = createNumber("gender", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath ming = createString("ming");

    public final StringPath mobileNumber = createString("mobileNumber");

    public final QOrder order;

    public final StringPath passportNumber = createString("passportNumber");

    public final StringPath secondName = createString("secondName");

    public final StringPath weichat = createString("weichat");

    public final StringPath xin = createString("xin");

    public QConfiremedTraveler(String variable) {
        this(ConfiremedTraveler.class, forVariable(variable), INITS);
    }

    public QConfiremedTraveler(Path<? extends ConfiremedTraveler> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QConfiremedTraveler(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QConfiremedTraveler(PathMetadata<?> metadata, PathInits inits) {
        this(ConfiremedTraveler.class, metadata, inits);
    }

    public QConfiremedTraveler(Class<? extends ConfiremedTraveler> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.order = inits.isInitialized("order") ? new QOrder(forProperty("order"), inits.get("order")) : null;
    }

}

