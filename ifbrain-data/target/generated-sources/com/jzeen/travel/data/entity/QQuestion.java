package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QQuestion is a Querydsl query type for Question
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QQuestion extends EntityPathBase<Question> {

    private static final long serialVersionUID = 1208560420L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuestion question = new QQuestion("question");

    public final StringPath answer = createString("answer");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final StringPath explaination = createString("explaination");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final ListPath<ItemManagementQuestion, QItemManagementQuestion> itemManagementList = this.<ItemManagementQuestion, QItemManagementQuestion>createList("itemManagementList", ItemManagementQuestion.class, QItemManagementQuestion.class, PathInits.DIRECT2);

    public final NumberPath<Integer> ordernumber = createNumber("ordernumber", Integer.class);

    public final StringPath questionNameContent = createString("questionNameContent");

    public final StringPath questionOptionContent = createString("questionOptionContent");

    public final QQuestionOptionImage questionOptionImage;

    public final QQuestionType questiontype;

    public final StringPath testdifficulty = createString("testdifficulty");

    public QQuestion(String variable) {
        this(Question.class, forVariable(variable), INITS);
    }

    public QQuestion(Path<? extends Question> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QQuestion(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QQuestion(PathMetadata<?> metadata, PathInits inits) {
        this(Question.class, metadata, inits);
    }

    public QQuestion(Class<? extends Question> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.questionOptionImage = inits.isInitialized("questionOptionImage") ? new QQuestionOptionImage(forProperty("questionOptionImage")) : null;
        this.questiontype = inits.isInitialized("questiontype") ? new QQuestionType(forProperty("questiontype"), inits.get("questiontype")) : null;
    }

}

