package com.jzeen.travel.data.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
@Entity
@Table(name = "t_picture_survey_relate")
@JsonIdentityInfo(generator = JSOGGenerator.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class SurveyImageRelate {

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SurveyFeedback getSurveyFeedback() {
		return surveyFeedback;
	}

	public void setSurveyFeedback(SurveyFeedback surveyFeedback) {
		this.surveyFeedback = surveyFeedback;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne(targetEntity = SurveyFeedback.class)
	@JoinColumn(name = "t_survey_feedback_id")
	private SurveyFeedback surveyFeedback;
	
	@OneToOne(targetEntity=Image.class)
    @JoinColumn(name="t_picture_id")
	private Image image;

}
