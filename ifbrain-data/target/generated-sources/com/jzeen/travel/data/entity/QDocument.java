package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QDocument is a Querydsl query type for Document
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDocument extends EntityPathBase<Document> {

    private static final long serialVersionUID = -1058815911L;

    public static final QDocument document = new QDocument("document");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final StringPath fileName = createString("fileName");

    public final StringPath filePath = createString("filePath");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final ListPath<InstructionFileRelate, QInstructionFileRelate> instructionfilerelate = this.<InstructionFileRelate, QInstructionFileRelate>createList("instructionfilerelate", InstructionFileRelate.class, QInstructionFileRelate.class, PathInits.DIRECT2);

    public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

    public QDocument(String variable) {
        super(Document.class, forVariable(variable));
    }

    public QDocument(Path<? extends Document> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDocument(PathMetadata<?> metadata) {
        super(Document.class, metadata);
    }

}

