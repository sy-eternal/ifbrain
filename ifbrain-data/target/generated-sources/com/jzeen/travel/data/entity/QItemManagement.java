package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QItemManagement is a Querydsl query type for ItemManagement
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QItemManagement extends EntityPathBase<ItemManagement> {

    private static final long serialVersionUID = -1016112012L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QItemManagement itemManagement = new QItemManagement("itemManagement");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final QExam exam;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath itemdescription = createString("itemdescription");

    public final ListPath<ItemManagementQuestion, QItemManagementQuestion> itemManagementList = this.<ItemManagementQuestion, QItemManagementQuestion>createList("itemManagementList", ItemManagementQuestion.class, QItemManagementQuestion.class, PathInits.DIRECT2);

    public final NumberPath<Integer> ordernumber = createNumber("ordernumber", Integer.class);

    public final StringPath perscore = createString("perscore");

    public final QQuestionType questiontype;

    public final StringPath title = createString("title");

    public QItemManagement(String variable) {
        this(ItemManagement.class, forVariable(variable), INITS);
    }

    public QItemManagement(Path<? extends ItemManagement> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QItemManagement(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QItemManagement(PathMetadata<?> metadata, PathInits inits) {
        this(ItemManagement.class, metadata, inits);
    }

    public QItemManagement(Class<? extends ItemManagement> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.exam = inits.isInitialized("exam") ? new QExam(forProperty("exam"), inits.get("exam")) : null;
        this.questiontype = inits.isInitialized("questiontype") ? new QQuestionType(forProperty("questiontype"), inits.get("questiontype")) : null;
    }

}

