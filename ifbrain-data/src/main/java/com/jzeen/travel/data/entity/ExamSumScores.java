package com.jzeen.travel.data.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

@Entity
@Table(name="t_exam_sumscores")
@JsonIdentityInfo(generator = JSOGGenerator.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ExamSumScores {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	//课程级别
	@ManyToOne(targetEntity = Child.class)
	@JoinColumn(name="child_id")
	private Child child;
	

	//总分数
	@Column(name="sum_score")
	private BigDecimal sumScore;

	//试题表
	@ManyToOne(targetEntity = Exam.class)
	@JoinColumn(name="exam_id")
	private Exam exam;
	
	@Column(name="create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd  HH:mm",timezone = "GMT+8")
	private Date createTime;

	
	//课程级别
		@ManyToOne(targetEntity = User.class)
		@JoinColumn(name="user_id")
		private User user;
//判断是否成绩合格
	@Transient
	private String result;
	
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Child getChild() {
		return child;
	}

	public void setChild(Child child) {
		this.child = child;
	}

	

	



	public BigDecimal getSumScore() {
		return sumScore;
	}

	public void setSumScore(BigDecimal sumScore) {
		this.sumScore = sumScore;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	
}
