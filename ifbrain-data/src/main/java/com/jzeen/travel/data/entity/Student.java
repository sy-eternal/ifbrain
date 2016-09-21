package com.jzeen.travel.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_student")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
		//学校名词
	
	    @Column(name = "student_name")
	    private String studentName;
		//创建时间
		@Column(name = "create_time")
	    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	    private Date createTime;
		//班级id
		 @ManyToOne(targetEntity = SchoolClass.class)
		 @JoinColumn(name = "t_school_class_id")
	//	 @JsonBackReference
		 private SchoolClass schoolClass;

		

		public Integer getId() {
			return id;
		}

	

		public void setId(Integer id) {
			this.id = id;
		}

		public String getStudentName() {
			return studentName;
		}

		public void setStudentName(String studentName) {
			this.studentName = studentName;
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

		
	
	



	
	
	
}
