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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OrderBy;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

@Entity
@Table(name = "t_tripblog")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class TripBlog {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	//游记评论
	@OneToMany(mappedBy = "tripBlog", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TripBlogComments> tripBlogComments;
	//游记标签
	@OneToMany(mappedBy = "tripBlog", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TripBlogTags> tripBlogTags;
	//游记条目
	@OneToMany(mappedBy = "tripBlog", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TripBlogItem> tripblogItem;

	//用户主键
	@ManyToOne(targetEntity=User.class)
	@JoinColumn(name="user_id")
	private User user;
	//游记封面主键
	@OneToOne(targetEntity=Image.class)
    @JoinColumn(name="cover_pic_id")
	private Image image;
	//游记时间
	@Column(name="time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	private Date time;
	//创建时间
	@Column(name="create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	private Date createTime;
	//游记标题
	@Column(name="tile")
	private String tile;
	//游记说
	@Column(name="description")
	private String description;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<TripBlogComments> getTripBlogComments() {
		return tripBlogComments;
	}
	public void setTripBlogComments(List<TripBlogComments> tripBlogComments) {
		this.tripBlogComments = tripBlogComments;
	}
	public List<TripBlogTags> getTripBlogTags() {
		return tripBlogTags;
	}
	public void setTripBlogTags(List<TripBlogTags> tripBlogTags) {
		this.tripBlogTags = tripBlogTags;
	}
	public List<TripBlogItem> getTripblogItem() {
		return tripblogItem;
	}
	public void setTripblogItem(List<TripBlogItem> tripblogItem) {
		this.tripblogItem = tripblogItem;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getTile() {
		return tile;
	}
	public void setTile(String tile) {
		this.tile = tile;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
