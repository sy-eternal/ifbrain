package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QAirline is a Querydsl query type for Airline
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QAirline extends EntityPathBase<Airline> {

    private static final long serialVersionUID = 54633792L;

    public static final QAirline airline = new QAirline("airline");

    public final StringPath airlineChinaName = createString("airlineChinaName");

    public final StringPath airlineCode = createString("airlineCode");

    public final StringPath airlineEnglishName = createString("airlineEnglishName");

    public final ListPath<FilghtPlan, QFilghtPlan> filghtPlan = this.<FilghtPlan, QFilghtPlan>createList("filghtPlan", FilghtPlan.class, QFilghtPlan.class, PathInits.DIRECT2);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QAirline(String variable) {
        super(Airline.class, forVariable(variable));
    }

    public QAirline(Path<? extends Airline> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAirline(PathMetadata<?> metadata) {
        super(Airline.class, metadata);
    }

}

