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

@Entity
@Table(name="t_purpose")
public class PurposeActive {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	
	@NotEmpty
	@Column(name="purpose")
	private String purposeactive;
	
	@Column(name="create_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd ")
    @JsonFormat(pattern = "yyyy-MM-dd ",timezone = "GMT+8")
	private Date createTime;
	
	@OneToOne(targetEntity=Image.class)
	@JoinColumn(name ="picture_id")
	private Image image;
	
//	@OneToOne
//	@JoinColumn(name = "picture_id")
//	@JsonIgnore
//	private Image image;
	
//	public Image getImage() {
//		return image;
//	}
//	public void setImage(Image image) {
//		this.image = image;
//	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getPurposeactive() {
		return purposeactive;
	}

	public void setPurposeactive(String purposeactive) {
		this.purposeactive = purposeactive;
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
}
