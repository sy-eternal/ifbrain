package com.jzeen.travel.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_class")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class CourseClass {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	//课程信_id

	 @ManyToOne(targetEntity = Course.class)
	 @JoinColumn(name = "t_c_id")
	 @JsonBackReference
	 private Course courseId;
	
	
	//课程级别
	@Column(name="course_level")
	private String courseLevel;
	
	//班级名称
	@Column(name="class_name")
	private String className;
	
	//课程id
	//@Column(name="course_id")
	//private Integer courseId;
	
	/*//孩子id
	@Column(name="child_id")
	private Integer childId;*/

	
	//班级开始日期
	@Column(name = "start_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd ")
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	private Date startDate;

	
	//班级结束日期
	@Column(name = "end_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd ")
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	private Date endDate;
	
	//创建日期
	@Column(name = "create_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd ")
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	private Date createDate;
	
	//孩子id
		@Column(name="child_number")
		private Integer childNumber;
		
		@OneToMany(mappedBy = "courseClass", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
		private List<Child> child;	
	



	public List<Child> getChild() {
			return child;
		}

		public void setChild(List<Child> child) {
			this.child = child;
		}

	public Integer getChildNumber() {
			return childNumber;
		}

		public void setChildNumber(Integer childNumber) {
			this.childNumber = childNumber;
		}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getCourseLevel() {
		return courseLevel;
	}

	public void setCourseLevel(String courseLevel) {
		this.courseLevel = courseLevel;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	

	
	public Course getCourseId() {
		return courseId;
	}

	public void setCourseId(Course courseId) {
		this.courseId = courseId;
	}

	

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}
