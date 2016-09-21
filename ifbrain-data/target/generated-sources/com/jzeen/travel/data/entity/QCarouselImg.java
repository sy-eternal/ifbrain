package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QCarouselImg is a Querydsl query type for CarouselImg
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCarouselImg extends EntityPathBase<CarouselImg> {

    private static final long serialVersionUID = -703356347L;

    public static final QCarouselImg carouselImg = new QCarouselImg("carouselImg");

    public final StringPath content = createString("content");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final StringPath fileName = createString("fileName");

    public final StringPath filePath = createString("filePath");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> sortnumber = createNumber("sortnumber", Integer.class);

    public QCarouselImg(String variable) {
        super(CarouselImg.class, forVariable(variable));
    }

    public QCarouselImg(Path<? extends CarouselImg> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCarouselImg(PathMetadata<?> metadata) {
        super(CarouselImg.class, metadata);
    }

}

