package com.jzeen.travel.data.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

@Entity
@Table(name = "t_video")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class Video {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	//班级id
	@Column(name="class_id")
	private Integer classId;

	//课程级别
	@Column(name="course_level")
	private String courseLevel;


	//课程序数
	@Column(name="ordinal_number")
	private Integer ordinalNumber;
	
	//课序名称
	@Column(name="lesson_name")
	private String lessonName;
	//视频路径
	@Column(name="video_path")
	private String videoPath;
	//创建日期
	@Column(name = "create_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd ")
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	private Date createTime;
	
	@ManyToOne(targetEntity = Child.class)
	@JoinColumn(name="child_id")
	@JsonBackReference
	private Child child;
	
	
	public Child getChild() {
		return child;
	}
	public void setChild(Child child) {
		this.child = child;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getClassId() {
		return classId;
	}
	public void setClassId(Integer classId) {
		this.classId = classId;
	}
	public String getCourseLevel() {
		return courseLevel;
	}
	public void setCourseLevel(String courseLevel) {
		this.courseLevel = courseLevel;
	}
	
	public Integer getOrdinalNumber() {
		return ordinalNumber;
	}
	public void setOrdinalNumber(Integer ordinalNumber) {
		this.ordinalNumber = ordinalNumber;
	}
	public String getLessonName() {
		return lessonName;
	}
	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}
	public String getVideoPath() {
		return videoPath;
	}
	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


}
