package com.jzeen.travel.data.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

@Entity
@Table(name="t_accompany_type")
@JsonIdentityInfo(generator = JSOGGenerator.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class AccompanyActionType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@NotEmpty
	@Column(name="accompany_type")
	private String accompanyType;
	
	
	@Column(name="create_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	private Date createTime;
	
//	@Column(name="picture_id")
//	private Integer pictureId;
	    @OneToOne(targetEntity=Image.class)
		@JoinColumn(name ="picture_id")
		private Image image;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccompanyType() {
		return accompanyType;
	}

	public void setAccompanyType(String accompanyType) {
		this.accompanyType = accompanyType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

//	public Integer getPictureId() {
//		return pictureId;
//	}
//
//	public void setPictureId(Integer pictureId) {
//		this.pictureId = pictureId;
//	}
	
	
}
