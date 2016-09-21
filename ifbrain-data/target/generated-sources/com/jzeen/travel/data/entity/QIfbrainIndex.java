package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QIfbrainIndex is a Querydsl query type for IfbrainIndex
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QIfbrainIndex extends EntityPathBase<IfbrainIndex> {

    private static final long serialVersionUID = 1512146071L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QIfbrainIndex ifbrainIndex1 = new QIfbrainIndex("ifbrainIndex1");

    public final NumberPath<Integer> afterclassIncome = createNumber("afterclassIncome", Integer.class);

    public final StringPath applicationtotalScore = createString("applicationtotalScore");

    public final NumberPath<java.math.BigDecimal> balance = createNumber("balance", java.math.BigDecimal.class);

    public final NumberPath<Integer> bankAfterclassIncome = createNumber("bankAfterclassIncome", Integer.class);

    public final QChild child;

    public final NumberPath<Integer> classId = createNumber("classId", Integer.class);

    public final ListPath<Comment, QComment> comment = this.<Comment, QComment>createList("comment", Comment.class, QComment.class, PathInits.DIRECT2);

    public final StringPath courseLevel = createString("courseLevel");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<java.math.BigDecimal> expense = createNumber("expense", java.math.BigDecimal.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> ifbrainIndex = createNumber("ifbrainIndex", Integer.class);

    public final ListPath<IfbrainVar, QIfbrainVar> ifbrainVar = this.<IfbrainVar, QIfbrainVar>createList("ifbrainVar", IfbrainVar.class, QIfbrainVar.class, PathInits.DIRECT2);

    public final NumberPath<java.math.BigDecimal> income = createNumber("income", java.math.BigDecimal.class);

    public final StringPath knowledgetotalScore = createString("knowledgetotalScore");

    public final StringPath lessonName = createString("lessonName");

    public final NumberPath<Integer> ordinalNumber = createNumber("ordinalNumber", Integer.class);

    public final NumberPath<Integer> primarysIfbrainindex = createNumber("primarysIfbrainindex", Integer.class);

    public final NumberPath<Integer> supplementaryTokens = createNumber("supplementaryTokens", Integer.class);

    public QIfbrainIndex(String variable) {
        this(IfbrainIndex.class, forVariable(variable), INITS);
    }

    public QIfbrainIndex(Path<? extends IfbrainIndex> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QIfbrainIndex(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QIfbrainIndex(PathMetadata<?> metadata, PathInits inits) {
        this(IfbrainIndex.class, metadata, inits);
    }

    public QIfbrainIndex(Class<? extends IfbrainIndex> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.child = inits.isInitialized("child") ? new QChild(forProperty("child"), inits.get("child")) : null;
    }

}

