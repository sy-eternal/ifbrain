package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QExamSumScores is a Querydsl query type for ExamSumScores
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QExamSumScores extends EntityPathBase<ExamSumScores> {

    private static final long serialVersionUID = -24044433L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QExamSumScores examSumScores = new QExamSumScores("examSumScores");

    public final QChild child;

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final QExam exam;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<java.math.BigDecimal> sumScore = createNumber("sumScore", java.math.BigDecimal.class);

    public final QUser user;

    public QExamSumScores(String variable) {
        this(ExamSumScores.class, forVariable(variable), INITS);
    }

    public QExamSumScores(Path<? extends ExamSumScores> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QExamSumScores(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QExamSumScores(PathMetadata<?> metadata, PathInits inits) {
        this(ExamSumScores.class, metadata, inits);
    }

    public QExamSumScores(Class<? extends ExamSumScores> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.child = inits.isInitialized("child") ? new QChild(forProperty("child"), inits.get("child")) : null;
        this.exam = inits.isInitialized("exam") ? new QExam(forProperty("exam"), inits.get("exam")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

