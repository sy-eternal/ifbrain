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
@Table(name = "t_free_file")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class FreeFile {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	//课程级别
	@ManyToOne(targetEntity = Course.class)
	@JoinColumn(name = "course_level_id", updatable = true)

	private Course courseLevel;


	//课程序数
	@ManyToOne(targetEntity = CourseCode.class)
	@JoinColumn(name = "course_code_id", updatable = true)
	
	private CourseCode courseCode;
	
	//名称
	@Column(name="name")
	private String fileName;
	//路径
	@Column(name="file_path")
	private String filePath;
	//创建日期
	@Column(name = "create_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:ss:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:ss:mm",timezone = "GMT+8")
	private Date createTime;
	
	
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
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}


	
	

	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


}
