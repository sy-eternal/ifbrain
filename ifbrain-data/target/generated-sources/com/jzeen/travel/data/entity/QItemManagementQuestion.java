package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QItemManagementQuestion is a Querydsl query type for ItemManagementQuestion
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QItemManagementQuestion extends EntityPathBase<ItemManagementQuestion> {

    private static final long serialVersionUID = 1773291642L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QItemManagementQuestion itemManagementQuestion = new QItemManagementQuestion("itemManagementQuestion");

    public final ListPath<ExamScores, QExamScores> examScores = this.<ExamScores, QExamScores>createList("examScores", ExamScores.class, QExamScores.class, PathInits.DIRECT2);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QItemManagement item;

    public final NumberPath<Integer> ordernumber = createNumber("ordernumber", Integer.class);

    public final QQuestion question;

    public QItemManagementQuestion(String variable) {
        this(ItemManagementQuestion.class, forVariable(variable), INITS);
    }

    public QItemManagementQuestion(Path<? extends ItemManagementQuestion> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QItemManagementQuestion(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QItemManagementQuestion(PathMetadata<?> metadata, PathInits inits) {
        this(ItemManagementQuestion.class, metadata, inits);
    }

    public QItemManagementQuestion(Class<? extends ItemManagementQuestion> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.item = inits.isInitialized("item") ? new QItemManagement(forProperty("item"), inits.get("item")) : null;
        this.question = inits.isInitialized("question") ? new QQuestion(forProperty("question"), inits.get("question")) : null;
    }

}

