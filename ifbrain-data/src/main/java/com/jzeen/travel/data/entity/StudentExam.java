package com.jzeen.travel.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
//中间表
@Entity
@Table(name = "t_student_exam")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class StudentExam {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	//试卷
	 @ManyToOne(targetEntity = Student.class)
	 @JoinColumn(name = "t_student_id")
	 private Student student;

		//试卷
		 @ManyToOne(targetEntity = Exam.class)
		 @JoinColumn(name = "t_exam_id")
		 private Exam exam;
		 
		//考试总成绩
		 @ManyToOne(targetEntity = ExamSumScores.class)
		 @JoinColumn(name = "t_exam_sumscores_id")
		 private ExamSumScores examsumscore;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Student getStudent() {
			return student;
		}

		public void setStudent(Student student) {
			this.student = student;
		}

		public Exam getExam() {
			return exam;
		}

		public void setExam(Exam exam) {
			this.exam = exam;
		}

		public ExamSumScores getExamsumscore() {
			return examsumscore;
		}

		public void setExamsumscore(ExamSumScores examsumscore) {
			this.examsumscore = examsumscore;
		}

		
	
	



	
	
	
}
