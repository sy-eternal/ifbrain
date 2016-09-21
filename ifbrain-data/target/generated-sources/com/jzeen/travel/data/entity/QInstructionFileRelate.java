package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QInstructionFileRelate is a Querydsl query type for InstructionFileRelate
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QInstructionFileRelate extends EntityPathBase<InstructionFileRelate> {

    private static final long serialVersionUID = 645093541L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInstructionFileRelate instructionFileRelate = new QInstructionFileRelate("instructionFileRelate");

    public final DateTimePath<java.util.Date> createtime = createDateTime("createtime", java.util.Date.class);

    public final QDocument document;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QVisaInstruction visainstruction;

    public QInstructionFileRelate(String variable) {
        this(InstructionFileRelate.class, forVariable(variable), INITS);
    }

    public QInstructionFileRelate(Path<? extends InstructionFileRelate> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QInstructionFileRelate(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QInstructionFileRelate(PathMetadata<?> metadata, PathInits inits) {
        this(InstructionFileRelate.class, metadata, inits);
    }

    public QInstructionFileRelate(Class<? extends InstructionFileRelate> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.document = inits.isInitialized("document") ? new QDocument(forProperty("document")) : null;
        this.visainstruction = inits.isInitialized("visainstruction") ? new QVisaInstruction(forProperty("visainstruction"), inits.get("visainstruction")) : null;
    }

}

