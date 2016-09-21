package com.jzeen.travel.data.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;


@Entity
@Table(name = "t_itemmanage_question")
@JsonIdentityInfo(generator = JSOGGenerator.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ItemManagementQuestion {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	
	@Column(name = "ordernumber")
	private int ordernumber;
	
	@ManyToOne
	@JoinColumn(name = "itemmanage_id", unique = true)
	private ItemManagement item;
	
	@ManyToOne
	@JoinColumn(name = "question_id", unique = true)
	private Question question;

	  @OneToMany(mappedBy = "itemManagementQuestion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	   	private List<ExamScores> examScores;
	     
	public List<ExamScores> getExamScores() {
		return examScores;
	}

	public void setExamScores(List<ExamScores> examScores) {
		this.examScores = examScores;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getOrdernumber() {
		return ordernumber;
	}

	public void setOrdernumber(int ordernumber) {
		this.ordernumber = ordernumber;
	}



	

	public ItemManagement getItem() {
		return item;
	}

	public void setItem(ItemManagement item) {
		this.item = item;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}
	
	
	
}
