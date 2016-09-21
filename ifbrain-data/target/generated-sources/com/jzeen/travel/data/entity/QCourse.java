package com.jzeen.travel.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCourse is a Querydsl query type for Course
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCourse extends EntityPathBase<Course> {

    private static final long serialVersionUID = -73890087L;

    public static final QCourse course = new QCourse("course");

    public final ListPath<CourseClass, QCourseClass> courseClass = this.<CourseClass, QCourseClass>createList("courseClass", CourseClass.class, QCourseClass.class, PathInits.DIRECT2);

    public final ListPath<CourseCode, QCourseCode> courseCode = this.<CourseCode, QCourseCode>createList("courseCode", CourseCode.class, QCourseCode.class, PathInits.DIRECT2);

    public final StringPath courseLevel = createString("courseLevel");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final ListPath<Exam, QExam> exam = this.<Exam, QExam>createList("exam", Exam.class, QExam.class, PathInits.DIRECT2);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath lessonName = createString("lessonName");

    public final NumberPath<Integer> ordinalNumber = createNumber("ordinalNumber", Integer.class);

    public final NumberPath<java.math.BigDecimal> price = createNumber("price", java.math.BigDecimal.class);

    public final StringPath type = createString("type");

    public QCourse(String variable) {
        super(Course.class, forVariable(variable));
    }

    public QCourse(Path<? extends Course> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCourse(PathMetadata<?> metadata) {
        super(Course.class, metadata);
    }

}

