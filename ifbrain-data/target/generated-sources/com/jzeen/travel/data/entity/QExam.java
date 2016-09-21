package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QExam is a Querydsl query type for Exam
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QExam extends EntityPathBase<Exam> {

    private static final long serialVersionUID = 916190813L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QExam exam = new QExam("exam");

    public final QCourse course;

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final StringPath creditsRequired = createString("creditsRequired");

    public final StringPath examDescription = createString("examDescription");

    public final StringPath examName = createString("examName");

    public final StringPath examTime = createString("examTime");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final ListPath<ItemManagement, QItemManagement> itemManagement = this.<ItemManagement, QItemManagement>createList("itemManagement", ItemManagement.class, QItemManagement.class, PathInits.DIRECT2);

    public final StringPath status = createString("status");

    public final StringPath testdifficulty = createString("testdifficulty");

    public final NumberPath<java.math.BigDecimal> totalAmount = createNumber("totalAmount", java.math.BigDecimal.class);

    public final StringPath unit = createString("unit");

    public QExam(String variable) {
        this(Exam.class, forVariable(variable), INITS);
    }

    public QExam(Path<? extends Exam> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QExam(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QExam(PathMetadata<?> metadata, PathInits inits) {
        this(Exam.class, metadata, inits);
    }

    public QExam(Class<? extends Exam> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.course = inits.isInitialized("course") ? new QCourse(forProperty("course")) : null;
    }

}

