package com.jzeen.travel.data.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

@Entity
@Table(name="t_exam_scores")
@JsonIdentityInfo(generator = JSOGGenerator.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ExamScores {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne(targetEntity = Child.class)
	@JoinColumn(name="child_id")
	private Child child;
	
	//考试中间表
	@ManyToOne(targetEntity = ItemManagementQuestion.class)
	@JoinColumn(name="itemmanage_question_id", updatable = true)
	private ItemManagementQuestion itemManagementQuestion;
	
	//第几题
	@Column(name="ordinalnumber")
	private Integer ordinalnumber;
	
	//child答案
	@Column(name="answer")
	private String answer;
	
	//回答状态  0，回答错误   1.回答正确
	@Column(name="answer_status")
	private Integer answerStatus;
	
	@Column(name="create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd  HH:mm",timezone = "GMT+8")
	private Date createTime;

	
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name="user_id")
	private User user;
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

	public ItemManagementQuestion getItemManagementQuestion() {
		return itemManagementQuestion;
	}

	public void setItemManagementQuestion(
			ItemManagementQuestion itemManagementQuestion) {
		this.itemManagementQuestion = itemManagementQuestion;
	}

	public Integer getOrdinalnumber() {
		return ordinalnumber;
	}

	public void setOrdinalnumber(Integer ordinalnumber) {
		this.ordinalnumber = ordinalnumber;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Integer getAnswerStatus() {
		return answerStatus;
	}

	public void setAnswerStatus(Integer answerStatus) {
		this.answerStatus = answerStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	
	
	
}
