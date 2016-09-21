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
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

@Entity
@Table(name = "t_course_code")
@JsonIdentityInfo(generator = JSOGGenerator.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CourseCode {
	//课程id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    //课程序数
    @Column(name="ordinal_number")
    private Integer ordinalNumber;
    //课程名称
    @Column(name="lesson_name")
    private String lessonName;
    //课程级别
    @Transient
    private String courselevel;
    
    
  //课程id
  
    @ManyToOne(targetEntity = Course.class)
	@JoinColumn(name = "course_id", updatable = true)
	private Course courseId;
    
    
 // 上课时间
 	@Column(name = "class_time")
 	@DateTimeFormat(pattern = "yyyy-MM-dd ")
 	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
 	private Date classTime;
 	//课程简介
    @Column(name="course_description")
    private String courseDescription;
    
    
	@OneToMany(mappedBy = "courseCode", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FreeVideo> freevideo;
	@OneToMany(mappedBy = "courseCode", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FreeFile> freefile;
	
	public String getCourselevel() {
		return courselevel;
	}
	public void setCourselevel(String courselevel) {
		this.courselevel = courselevel;
	}
	public List<FreeVideo> getFreevideo() {
		return freevideo;
	}
	public void setFreevideo(List<FreeVideo> freevideo) {
		this.freevideo = freevideo;
	}
	public List<FreeFile> getFreefile() {
		return freefile;
	}
	public void setFreefile(List<FreeFile> freefile) {
		this.freefile = freefile;
	}
	public Date getClassTime() {
		return classTime;
	}
	public void setClassTime(Date classTime) {
		this.classTime = classTime;
	}
	public String getCourseDescription() {
		return courseDescription;
	}
	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getOrdinalNumber() {
		return ordinalNumber;
	}
	public void setOrdinalNumber(Integer ordinalNumber) {
		this.ordinalNumber = ordinalNumber;
	}
	public Course getCourseId() {
		return courseId;
	}
	public void setCourseId(Course courseId) {
		this.courseId = courseId;
	}
	public String getLessonName() {
		return lessonName;
	}
	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}
	
	

   


  	
 
	


    
  
}
