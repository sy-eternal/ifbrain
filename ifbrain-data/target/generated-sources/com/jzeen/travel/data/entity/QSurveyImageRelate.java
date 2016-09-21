package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSurveyImageRelate is a Querydsl query type for SurveyImageRelate
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSurveyImageRelate extends EntityPathBase<SurveyImageRelate> {

    private static final long serialVersionUID = 381243228L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSurveyImageRelate surveyImageRelate = new QSurveyImageRelate("surveyImageRelate");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QImage image;

    public final QSurveyFeedback surveyFeedback;

    public QSurveyImageRelate(String variable) {
        this(SurveyImageRelate.class, forVariable(variable), INITS);
    }

    public QSurveyImageRelate(Path<? extends SurveyImageRelate> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSurveyImageRelate(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSurveyImageRelate(PathMetadata<?> metadata, PathInits inits) {
        this(SurveyImageRelate.class, metadata, inits);
    }

    public QSurveyImageRelate(Class<? extends SurveyImageRelate> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.image = inits.isInitialized("image") ? new QImage(forProperty("image"), inits.get("image")) : null;
        this.surveyFeedback = inits.isInitialized("surveyFeedback") ? new QSurveyFeedback(forProperty("surveyFeedback"), inits.get("surveyFeedback")) : null;
    }

}

