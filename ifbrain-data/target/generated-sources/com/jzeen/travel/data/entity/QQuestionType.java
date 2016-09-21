package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QQuestionType is a Querydsl query type for QuestionType
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QQuestionType extends EntityPathBase<QuestionType> {

    private static final long serialVersionUID = 2074016894L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuestionType questionType = new QQuestionType("questionType");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QItemTemplate itemTemplate;

    public final NumberPath<Integer> orderNumber = createNumber("orderNumber", Integer.class);

    public final ListPath<Question, QQuestion> question = this.<Question, QQuestion>createList("question", Question.class, QQuestion.class, PathInits.DIRECT2);

    public final StringPath questionTypeName = createString("questionTypeName");

    public QQuestionType(String variable) {
        this(QuestionType.class, forVariable(variable), INITS);
    }

    public QQuestionType(Path<? extends QuestionType> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QQuestionType(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QQuestionType(PathMetadata<?> metadata, PathInits inits) {
        this(QuestionType.class, metadata, inits);
    }

    public QQuestionType(Class<? extends QuestionType> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.itemTemplate = inits.isInitialized("itemTemplate") ? new QItemTemplate(forProperty("itemTemplate")) : null;
    }

}

