package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QStudentExam is a Querydsl query type for StudentExam
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QStudentExam extends EntityPathBase<StudentExam> {

    private static final long serialVersionUID = 902363388L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudentExam studentExam = new QStudentExam("studentExam");

    public final QExam exam;

    public final QExamSumScores examsumscore;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QStudent student;

    public QStudentExam(String variable) {
        this(StudentExam.class, forVariable(variable), INITS);
    }

    public QStudentExam(Path<? extends StudentExam> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStudentExam(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStudentExam(PathMetadata<?> metadata, PathInits inits) {
        this(StudentExam.class, metadata, inits);
    }

    public QStudentExam(Class<? extends StudentExam> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.exam = inits.isInitialized("exam") ? new QExam(forProperty("exam"), inits.get("exam")) : null;
        this.examsumscore = inits.isInitialized("examsumscore") ? new QExamSumScores(forProperty("examsumscore"), inits.get("examsumscore")) : null;
        this.student = inits.isInitialized("student") ? new QStudent(forProperty("student"), inits.get("student")) : null;
    }

}

