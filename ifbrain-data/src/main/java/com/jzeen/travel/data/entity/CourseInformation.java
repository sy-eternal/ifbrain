package com.jzeen.travel.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_course_information")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class CourseInformation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
		
		//创建时间
		@Column(name = "create_time")
	    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	    private Date createTime;
	
		//班级id
		 @ManyToOne(targetEntity = SchoolClass.class)
		 @JoinColumn(name = "school_class_id")
		 private SchoolClass schoolClass;
		 
		 //第几节课
		 @Column(name="ordinal_number")
		 private Integer ordinalNumber;
		 
		 //描述
		 @Column(name="description")
		 private String description;
		 //课程名称
		 @Column(name="lesson_name")
		 private String lessonName;
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public Date getCreateTime() {
			return createTime;
		}
		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
		public SchoolClass getSchoolClass() {
			return schoolClass;
		}
		public void setSchoolClass(SchoolClass schoolClass) {
			this.schoolClass = schoolClass;
		}
		public Integer getOrdinalNumber() {
			return ordinalNumber;
		}
		public void setOrdinalNumber(Integer ordinalNumber) {
			this.ordinalNumber = ordinalNumber;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getLessonName() {
			return lessonName;
		}
		public void setLessonName(String lessonName) {
			this.lessonName = lessonName;
		}

}
