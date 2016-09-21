package com.jzeen.travel.data.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
//考试系统题型模板
@Entity
@Table(name = "t_item_template")
@JsonIdentityInfo(generator = JSOGGenerator.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ItemTemplate {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	
	 //模板名称
    @Column(name="template_name")
    private String templateName;
    
    @OneToMany(mappedBy = "itemTemplate", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<QuestionType> questionType;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public List<QuestionType> getQuestionType() {
		return questionType;
	}

	public void setQuestionType(List<QuestionType> questionType) {
		this.questionType = questionType;
	}
   
    
    
    
    
}
