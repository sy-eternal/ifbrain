package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QMaterialComment is a Querydsl query type for MaterialComment
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMaterialComment extends EntityPathBase<MaterialComment> {

    private static final long serialVersionUID = -1043044806L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMaterialComment materialComment = new QMaterialComment("materialComment");

    public final ListPath<CommentReply, QCommentReply> commentReply = this.<CommentReply, QCommentReply>createList("commentReply", CommentReply.class, QCommentReply.class, PathInits.DIRECT2);

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QMaterial material;

    public final QUser user;

    public final StringPath usercomment = createString("usercomment");

    public QMaterialComment(String variable) {
        this(MaterialComment.class, forVariable(variable), INITS);
    }

    public QMaterialComment(Path<? extends MaterialComment> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMaterialComment(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMaterialComment(PathMetadata<?> metadata, PathInits inits) {
        this(MaterialComment.class, metadata, inits);
    }

    public QMaterialComment(Class<? extends MaterialComment> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.material = inits.isInitialized("material") ? new QMaterial(forProperty("material"), inits.get("material")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

