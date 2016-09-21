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
@Table(name = "t_free_video")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class FreeVideo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	//课程级别
	@ManyToOne(targetEntity = Course.class)
	@JoinColumn(name="course_level_id")
	private Course courseLevel;


	//课程序数
	@ManyToOne(targetEntity = CourseCode.class)
	@JoinColumn(name="course_code_id")
	private CourseCode courseCode;
	
	//视频名称
	@Column(name="video_name")
	private String videoName;
	//视频路径
	@Column(name="video_path")
	private String videoPath;
	//创建日期
	@Column(name = "create_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:ss:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:ss:mm",timezone = "GMT+8")
	private Date createTime;
	
	// 章节
	@ManyToOne(targetEntity = MaterialType.class)
	@JoinColumn(name = "materialtype_id", updatable = true)
	private MaterialType materialType;
	
	//视频中文名称
	@Column(name="video_cname")
	private String videoCName;
	
	
	
	public String getVideoCName() {
		return videoCName;
	}
	public void setVideoCName(String videoCName) {
		this.videoCName = videoCName;
	}
	public MaterialType getMaterialType() {
		return materialType;
	}
	public void setMaterialType(MaterialType materialType) {
		this.materialType = materialType;
	}
	public Course getCourseLevel() {
		return courseLevel;
	}
	public void setCourseLevel(Course courseLevel) {
		this.courseLevel = courseLevel;
	}
	public CourseCode getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(CourseCode courseCode) {
		this.courseCode = courseCode;
	}
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
