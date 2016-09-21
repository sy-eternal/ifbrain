package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QItemTemplate is a Querydsl query type for ItemTemplate
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QItemTemplate extends EntityPathBase<ItemTemplate> {

    private static final long serialVersionUID = 1553008875L;

    public static final QItemTemplate itemTemplate = new QItemTemplate("itemTemplate");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final ListPath<QuestionType, QQuestionType> questionType = this.<QuestionType, QQuestionType>createList("questionType", QuestionType.class, QQuestionType.class, PathInits.DIRECT2);

    public final StringPath templateName = createString("templateName");

    public QItemTemplate(String variable) {
        super(ItemTemplate.class, forVariable(variable));
    }

    public QItemTemplate(Path<? extends ItemTemplate> path) {
        super(path.getType(), path.getMetadata());
    }

    public QItemTemplate(PathMetadata<?> metadata) {
        super(ItemTemplate.class, metadata);
    }

}

