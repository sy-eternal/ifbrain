package com.jzeen.travel.data.entity;

//财脑课程和代币训练下的文章
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
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
@Entity
@Table(name = "t_publicmaterial")
@JsonIdentityInfo(generator = JSOGGenerator.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Publicmaterial {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	 //线下素材主题
    @Column(name="publicmaterial_name")
    private String publicmaterialName;
    //线下素材内容    
    @Column(name="publicmaterial_content")
    private Blob publicmaterialContent;
    //作者
    @Column(name="author")
    private String author;
    //线下素材创建时间
    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    //素材封面
   	@OneToOne(targetEntity = HeadPortrait.class)
   	@JoinColumn(name = "headportrait_id", updatable = true)
   	private HeadPortrait headPortrait;
    //类型
	@ManyToOne(targetEntity = NavigationbarModule.class)
	@JoinColumn(name = "navigationbarmodule_id")
	private NavigationbarModule navigationbarmodule;	
	
	//
	@Transient
	private String content;
	
	//访问量
		@Column(name = "visitor_number")
		private Integer visitornumber;

	public Integer getVisitornumber() {
			return visitornumber;
		}
		public void setVisitornumber(Integer visitornumber) {
			this.visitornumber = visitornumber;
		}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public String getPublicmaterialName() {
		return publicmaterialName;
	}
	public void setPublicmaterialName(String publicmaterialName) {
		this.publicmaterialName = publicmaterialName;
	}
	public Blob getPublicmaterialContent() {
		return publicmaterialContent;
	}
	public void setPublicmaterialContent(Blob publicmaterialContent) {
		this.publicmaterialContent = publicmaterialContent;
	}
	public HeadPortrait getHeadPortrait() {
		return headPortrait;
	}
	public void setHeadPortrait(HeadPortrait headPortrait) {
		this.headPortrait = headPortrait;
	}
	public NavigationbarModule getNavigationbarmodule() {
		return navigationbarmodule;
	}
	public void setNavigationbarmodule(NavigationbarModule navigationbarmodule) {
		this.navigationbarmodule = navigationbarmodule;
	}
	
	
	
	
    
    
    
    
}
