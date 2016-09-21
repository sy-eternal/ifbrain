package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCommentReply is a Querydsl query type for CommentReply
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCommentReply extends EntityPathBase<CommentReply> {

    private static final long serialVersionUID = -1265666039L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCommentReply commentReply = new QCommentReply("commentReply");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QMaterialComment materialComment;

    public final QMember member;

    public final StringPath membercomment = createString("membercomment");

    public final DateTimePath<java.util.Date> membercommentTime = createDateTime("membercommentTime", java.util.Date.class);

    public QCommentReply(String variable) {
        this(CommentReply.class, forVariable(variable), INITS);
    }

    public QCommentReply(Path<? extends CommentReply> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCommentReply(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCommentReply(PathMetadata<?> metadata, PathInits inits) {
        this(CommentReply.class, metadata, inits);
    }

    public QCommentReply(Class<? extends CommentReply> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.materialComment = inits.isInitialized("materialComment") ? new QMaterialComment(forProperty("materialComment"), inits.get("materialComment")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

