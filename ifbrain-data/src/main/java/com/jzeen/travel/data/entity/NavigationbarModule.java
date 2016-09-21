package com.jzeen.travel.data.entity;

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
@Table(name = "t_navigationbar_module")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class NavigationbarModule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    //标题
    @Column(name="title")
    private String title;
    //内容    
    @Column(name="content")
    private Blob content;
    //作者
    @Column(name="author")
    private String author;
   
    //创建时间
    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    //头像id
	@OneToOne(targetEntity = HeadPortrait.class)
	@JoinColumn(name = "headportrait_id", updatable = true)
	private HeadPortrait headPortrait;
	
    //导航栏id
	@ManyToOne(targetEntity = NavigationBar.class)
	@JoinColumn(name = "navigationbar_id")
	private NavigationBar navigationBar;
	
	
	//类型
	@Column(name="type")
	private Integer type;

	
	 //简写
    @Column(name="short_type_name")
    private String shorttitle;
    
	

	

	public String getShorttitle() {
		return shorttitle;
	}

	public void setShorttitle(String shorttitle) {
		this.shorttitle = shorttitle;
	}

	public Integer getType() {
			return type;
		}

		public void setType(Integer type) {
			this.type = type;
		}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Blob getContent() {
		return content;
	}

	public void setContent(Blob content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public HeadPortrait getHeadPortrait() {
		return headPortrait;
	}

	public void setHeadPortrait(HeadPortrait headPortrait) {
		this.headPortrait = headPortrait;
	}

	public NavigationBar getNavigationBar() {
		return navigationBar;
	}

	public void setNavigationBar(NavigationBar navigationBar) {
		this.navigationBar = navigationBar;
	}	

   
   }
