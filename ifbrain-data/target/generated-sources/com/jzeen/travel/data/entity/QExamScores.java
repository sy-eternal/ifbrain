package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QExamScores is a Querydsl query type for ExamScores
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QExamScores extends EntityPathBase<ExamScores> {

    private static final long serialVersionUID = -1640465186L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QExamScores examScores = new QExamScores("examScores");

    public final StringPath answer = createString("answer");

    public final NumberPath<Integer> answerStatus = createNumber("answerStatus", Integer.class);

    public final QChild child;

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QItemManagementQuestion itemManagementQuestion;

    public final NumberPath<Integer> ordinalnumber = createNumber("ordinalnumber", Integer.class);

    public final QUser user;

    public QExamScores(String variable) {
        this(ExamScores.class, forVariable(variable), INITS);
    }

    public QExamScores(Path<? extends ExamScores> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QExamScores(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QExamScores(PathMetadata<?> metadata, PathInits inits) {
        this(ExamScores.class, metadata, inits);
    }

    public QExamScores(Class<? extends ExamScores> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.child = inits.isInitialized("child") ? new QChild(forProperty("child"), inits.get("child")) : null;
        this.itemManagementQuestion = inits.isInitialized("itemManagementQuestion") ? new QItemManagementQuestion(forProperty("itemManagementQuestion"), inits.get("itemManagementQuestion")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

