package com.jzeen.travel.data.entity;

//家长手册
import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
@Entity
@Table(name = "t_parent_manual")
@JsonIdentityInfo(generator = JSOGGenerator.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ParentManual {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	 //家长手册标题
    @Column(name="manual_name")
    private String manualName;
    //手册内容   
    @Column(name="manual_content")
    private Blob manualContent;
    //作者
    @Column(name="author")
    private String author;
    //素材创建时间
    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    //素材封面
	@OneToOne(targetEntity = ParentManualImage.class)
	@JoinColumn(name = "manual_image_id", updatable = true)
	private ParentManualImage parentmanualimage;
    //类型
	@ManyToOne(targetEntity = Course.class)
	@JoinColumn(name = "course_id")
	private Course course;	
	//第几节课
	@Column(name = "ordinal_number")
	private Integer ordinalNumber;
	
	//访问量
	@Column(name = "visitor_number")
	private Integer visitornumber;
		
	public Integer getVisitornumber() {
			return visitornumber;
		}
		public void setVisitornumber(Integer visitornumber) {
			this.visitornumber = visitornumber;
		}
	public Integer getOrdinalNumber() {
		return ordinalNumber;
	}
	public void setOrdinalNumber(Integer ordinalNumber) {
		this.ordinalNumber = ordinalNumber;
	}
	public String getManualName() {
		return manualName;
	}
	public void setManualName(String manualName) {
		this.manualName = manualName;
	}
	public Blob getManualContent() {
		return manualContent;
	}
	public void setManualContent(Blob manualContent) {
		this.manualContent = manualContent;
	}
	public ParentManualImage getParentmanualimage() {
		return parentmanualimage;
	}
	public void setParentmanualimage(ParentManualImage parentmanualimage) {
		this.parentmanualimage = parentmanualimage;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
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
    
}
