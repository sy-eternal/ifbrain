package com.jzeen.travel.data.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
@Entity
@Table(name = "t_tripblog_comments")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class TripBlogComments {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	//用户主键
	@ManyToOne(targetEntity=User.class)
	@JoinColumn(name="user_id")
	private User user;
	//游记主键
	@ManyToOne(targetEntity=TripBlog.class)
	@JoinColumn(name="tripblog_id", updatable = true)
	private TripBlog tripBlog;
	//评论内容
	@Column(name="comment")
	private String comment;
	//创建时间
	@Column(name="create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	private Date createTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public TripBlog getTripBlog() {
		return tripBlog;
	}
	public void setTripBlog(TripBlog tripBlog) {
		this.tripBlog = tripBlog;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
