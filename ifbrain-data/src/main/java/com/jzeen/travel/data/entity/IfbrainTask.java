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
//财脑任务
@Entity
@Table(name = "t_ifbrain_task")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class IfbrainTask {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	//课程级别
	@ManyToOne(targetEntity = Course.class)
	@JoinColumn(name = "course_level_id", updatable = true)
	private Course courseLevel;
	//孩子
	@ManyToOne(targetEntity = Child.class)
	@JoinColumn(name = "child_id")
	private Child child;

	//课程序数
	@ManyToOne(targetEntity = CourseCode.class)
	@JoinColumn(name = "course_code_id", updatable = true)
	private CourseCode courseCode;
	
	//路径
	@Column(name="ifbrain_task_path")
	private String ifbrainTaskPath;
	//名称
	@Column(name="ifbrain_task_name")
	private String ifbrainTaskName;
	//任务描述
	@Column(name="description")
	private String description;
	//创建日期
	@Column(name = "create_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:ss:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:ss:mm",timezone = "GMT+8")
	private Date createTime;
	//财脑任务区别字段  0标识老师上传的任务,1代表孩子上传的任务
	@Column(name = "type")
	private String type;
	
	// 章节
	@ManyToOne(targetEntity = MaterialType.class)
	@JoinColumn(name = "materialtype_id", updatable = true)
	private MaterialType materialType;

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

	  public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
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

	public String getIfbrainTaskPath() {
		return ifbrainTaskPath;
	}
	public void setIfbrainTaskPath(String ifbrainTaskPath) {
		this.ifbrainTaskPath = ifbrainTaskPath;
	}
	public String getIfbrainTaskName() {
		return ifbrainTaskName;
	}
	public void setIfbrainTaskName(String ifbrainTaskName) {
		this.ifbrainTaskName = ifbrainTaskName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


}
