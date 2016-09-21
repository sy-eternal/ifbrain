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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

@Entity
@Table(name = "t_school")
@JsonIdentityInfo(generator = JSOGGenerator.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
//学校名称
   
    @Column(name = "sc_name")
    private String scName;
    
    
    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    
    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<SchoolClass> schoolClass;

    
    
  //管理权限
  		@ManyToOne(targetEntity = Member.class)
  		@JoinColumn(name="member_id")
  		private Member member;
  	//城市
  		
  	  @ManyToOne(targetEntity = CitySchool.class)
      @JoinColumn(name = "t_city_school_id", updatable = true)
      private CitySchool citySchool;
  	  
  	  
	public CitySchool getCitySchool() {
		return citySchool;
	}

	public void setCitySchool(CitySchool citySchool) {
		this.citySchool = citySchool;
	}

	public Member getMember() {
			return member;
		}

		public void setMember(Member member) {
			this.member = member;
		}

	public List<SchoolClass> getSchoolClass() {
		return schoolClass;
	}

	public void setSchoolClass(List<SchoolClass> schoolClass) {
		this.schoolClass = schoolClass;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getScName() {
		return scName;
	}

	public void setScName(String scName) {
		this.scName = scName;
	}

   

}
