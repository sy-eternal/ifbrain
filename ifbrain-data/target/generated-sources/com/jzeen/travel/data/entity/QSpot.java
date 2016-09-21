package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSpot is a Querydsl query type for Spot
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSpot extends EntityPathBase<Spot> {

    private static final long serialVersionUID = 916600640L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSpot spot = new QSpot("spot");

    public final StringPath address = createString("address");

    public final QCity cityid;

    public final StringPath clothingtips = createString("clothingtips");

    public final StringPath comguide = createString("comguide");

    public final StringPath cost = createString("cost");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final StringPath enlocation = createString("enlocation");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QImage image;

    public final StringPath location = createString("location");

    public final StringPath salesformula = createString("salesformula");

    public final StringPath specialnotes = createString("specialnotes");

    public final StringPath spendingtips = createString("spendingtips");

    public final StringPath spotsdescription = createString("spotsdescription");

    public final StringPath spotsename = createString("spotsename");

    public final StringPath spotsname = createString("spotsname");

    public final StringPath spotssummary = createString("spotssummary");

    public final ListPath<SpotTags, QSpotTags> spotTags = this.<SpotTags, QSpotTags>createList("spotTags", SpotTags.class, QSpotTags.class, PathInits.DIRECT2);

    public final ListPath<SpotTicketType, QSpotTicketType> spotTicketTypes = this.<SpotTicketType, QSpotTicketType>createList("spotTicketTypes", SpotTicketType.class, QSpotTicketType.class, PathInits.DIRECT2);

    public final QSupplier supplierid;

    public final StringPath tel = createString("tel");

    public final ListPath<SpotThemeRelate, QSpotThemeRelate> themeRelates = this.<SpotThemeRelate, QSpotThemeRelate>createList("themeRelates", SpotThemeRelate.class, QSpotThemeRelate.class, PathInits.DIRECT2);

    public final StringPath weathertips = createString("weathertips");

    public QSpot(String variable) {
        this(Spot.class, forVariable(variable), INITS);
    }

    public QSpot(Path<? extends Spot> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSpot(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSpot(PathMetadata<?> metadata, PathInits inits) {
        this(Spot.class, metadata, inits);
    }

    public QSpot(Class<? extends Spot> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cityid = inits.isInitialized("cityid") ? new QCity(forProperty("cityid"), inits.get("cityid")) : null;
        this.image = inits.isInitialized("image") ? new QImage(forProperty("image"), inits.get("image")) : null;
        this.supplierid = inits.isInitialized("supplierid") ? new QSupplier(forProperty("supplierid")) : null;
    }

}

