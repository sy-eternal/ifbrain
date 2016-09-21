package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QQuestionOptionImage is a Querydsl query type for QuestionOptionImage
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QQuestionOptionImage extends EntityPathBase<QuestionOptionImage> {

    private static final long serialVersionUID = -1058271198L;

    public static final QQuestionOptionImage questionOptionImage = new QQuestionOptionImage("questionOptionImage");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final StringPath fileName = createString("fileName");

    public final StringPath filePath = createString("filePath");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QQuestionOptionImage(String variable) {
        super(QuestionOptionImage.class, forVariable(variable));
    }

    public QQuestionOptionImage(Path<? extends QuestionOptionImage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QQuestionOptionImage(PathMetadata<?> metadata) {
        super(QuestionOptionImage.class, metadata);
    }

}

