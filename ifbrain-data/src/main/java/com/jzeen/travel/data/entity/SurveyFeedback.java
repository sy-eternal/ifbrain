package com.jzeen.travel.data.entity;

import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
@Entity
@Table(name = "t_survey_feedback")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class SurveyFeedback {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "user_id")
	private User user;
	
	
	  @Column(name="username")
      private String username;
	  
	  @Column(name="content")
      private String content;
	  
	  public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<SurveyImageRelate> getSurveyImageRelate() {
		return surveyImageRelate;
	}

	public void setSurveyImageRelate(List<SurveyImageRelate> surveyImageRelate) {
		this.surveyImageRelate = surveyImageRelate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFunctionTypeId() {
		return functionTypeId;
	}

	public void setFunctionTypeId(String functionTypeId) {
		this.functionTypeId = functionTypeId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@OneToMany(mappedBy = "surveyFeedback", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SurveyImageRelate> surveyImageRelate;
	  

	  @Column(name="title")
      private String title;
	  

	  @Column(name="t_function_type_id")
      private String functionTypeId;
	  
	  @Column(name = "create_time")
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
		private Date createTime;
}
