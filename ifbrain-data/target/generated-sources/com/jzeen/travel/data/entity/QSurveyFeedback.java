package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSurveyFeedback is a Querydsl query type for SurveyFeedback
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSurveyFeedback extends EntityPathBase<SurveyFeedback> {

    private static final long serialVersionUID = -2031745603L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSurveyFeedback surveyFeedback = new QSurveyFeedback("surveyFeedback");

    public final StringPath content = createString("content");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final StringPath functionTypeId = createString("functionTypeId");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final ListPath<SurveyImageRelate, QSurveyImageRelate> surveyImageRelate = this.<SurveyImageRelate, QSurveyImageRelate>createList("surveyImageRelate", SurveyImageRelate.class, QSurveyImageRelate.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    public final QUser user;

    public final StringPath username = createString("username");

    public QSurveyFeedback(String variable) {
        this(SurveyFeedback.class, forVariable(variable), INITS);
    }

    public QSurveyFeedback(Path<? extends SurveyFeedback> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSurveyFeedback(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSurveyFeedback(PathMetadata<?> metadata, PathInits inits) {
        this(SurveyFeedback.class, metadata, inits);
    }

    public QSurveyFeedback(Class<? extends SurveyFeedback> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

