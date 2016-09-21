package com.jzeen.travel.data.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_school_class")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class SchoolClass {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
		//学校名词

	    @Column(name = "name")
	    private String name;
		//创建时间
		@Column(name = "create_time")
	    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	    private Date createTime;
		//学校_id
		 @ManyToOne(targetEntity = School.class)
		 @JoinColumn(name = "t_school_id")
		// @JsonBackReference
		 private School school;

		 
		   @OneToMany(mappedBy = "schoolClass", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
			private List<Student> student;
		   
		  //管理权限
		  		@ManyToOne(targetEntity = Member.class)
		  		@JoinColumn(name="t_member_id")
		  		private Member member;
		  		
		  		
		  		
		

		public List<Student> getStudent() {
					return student;
				}

				public void setStudent(List<Student> student) {
					this.student = student;
				}

		public Member getMember() {
					return member;
				}

				public void setMember(Member member) {
					this.member = member;
				}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

		public School getSchool() {
			return school;
		}

		public void setSchool(School school) {
			this.school = school;
		}

		
	
	



	
	
	
}
