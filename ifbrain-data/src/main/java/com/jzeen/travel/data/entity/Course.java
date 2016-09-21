package com.jzeen.travel.data.entity;

import java.math.BigDecimal;
import java.util.Date;
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

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

@Entity
@Table(name = "t_course")
@JsonIdentityInfo(generator = JSOGGenerator.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Course {
	// 课程id
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	// 课程级别
	@Column(name = "course_level")
	private String courseLevel;
	// 课程价格
	@Column(name = "price")
	private BigDecimal price;
	// 课程序数
	@Column(name = "ordinal_number")
	private Integer ordinalNumber;
	// 课序名称
	@Column(name = "lesson_name")
	private String lessonName;
	// 类型,中英文区别//0 中文 1 英文
	@Column(name = "type")
	private String type;

	// 创建日期
	@Column(name = "create_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd ")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date createTime;

	@OneToMany(mappedBy = "courseId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CourseClass> courseClass;

	@OneToMany(mappedBy = "courseId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CourseCode> courseCode;

	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Exam> exam;
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<CourseClass> getCourseClass() {
		return courseClass;
	}

	public void setCourseClass(List<CourseClass> courseClass) {
		this.courseClass = courseClass;
	}

	public List<CourseCode> getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(List<CourseCode> courseCode) {
		this.courseCode = courseCode;
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<Exam> getExam() {
		return exam;
	}

	public void setExam(List<Exam> exam) {
		this.exam = exam;
	}

	/*
	 * //课程name
	 * 
	 * @Column(name = "class_name") private String className;
	 */

	// 人数
	/*
	 * @Column(name = "person_number") private Integer personNumber;
	 */

	// 对应订单号
	/* @ManyToOne(targetEntity = CourseOrder.class) */
	/*
	 * @JoinColumn(name = "order_id", updatable = true) private Integer
	 * courseOrder;
	 */
	/*
	 * @Column(name = "order_id", updatable = true) private Integer courseOrder;
	 */

}
