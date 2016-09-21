package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QVisaInstruction is a Querydsl query type for VisaInstruction
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QVisaInstruction extends EntityPathBase<VisaInstruction> {

    private static final long serialVersionUID = 1154475151L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVisaInstruction visaInstruction = new QVisaInstruction("visaInstruction");

    public final DateTimePath<java.util.Date> createtime = createDateTime("createtime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QImage image;

    public final ListPath<InstructionFileRelate, QInstructionFileRelate> instructionfilerelate = this.<InstructionFileRelate, QInstructionFileRelate>createList("instructionfilerelate", InstructionFileRelate.class, QInstructionFileRelate.class, PathInits.DIRECT2);

    public final StringPath processName = createString("processName");

    public QVisaInstruction(String variable) {
        this(VisaInstruction.class, forVariable(variable), INITS);
    }

    public QVisaInstruction(Path<? extends VisaInstruction> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVisaInstruction(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVisaInstruction(PathMetadata<?> metadata, PathInits inits) {
        this(VisaInstruction.class, metadata, inits);
    }

    public QVisaInstruction(Class<? extends VisaInstruction> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.image = inits.isInitialized("image") ? new QImage(forProperty("image"), inits.get("image")) : null;
    }

}

