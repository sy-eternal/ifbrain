package com.jzeen.travel.data.entity;


import java.util.Date;
import java.util.List;
import javax.persistence.Transient;
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

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
@Entity
@Table(name = "t_tripblog_item")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class TripBlogItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	// 游记主键
	@ManyToOne(targetEntity = TripBlog.class)
	@JoinColumn(name = "tripblog_id")
	private TripBlog tripBlog;
	// 图片主键
	@OneToMany(mappedBy = "tripblogItem", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TripBlogItemImageRelate> tripBlogItemImageRelate;
	// 城市
	@ManyToOne(targetEntity = City.class)
	@JoinColumn(name = "city_id")
	private City city;
	// 英文名称
	@Column(name = "eng_name")
	private String eng_name;
	// 中文名称
	@Column(name = "ch_name")
	private String ch_name;
	// 介绍
	@Column(name = "description")
	private String description;
	// 小贴士
	@Column(name = "tips")
	private String tips;
	// 创建时间
	@Column(name = "create_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date createTime;
	
	@Transient
    private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public TripBlog getTripBlog() {
		return tripBlog;
	}
	public void setTripBlog(TripBlog tripBlog) {
		this.tripBlog = tripBlog;
	}

	public List<TripBlogItemImageRelate> getTripBlogItemImageRelate() {
		return tripBlogItemImageRelate;
	}
	public void setTripBlogItemImageRelate(
			List<TripBlogItemImageRelate> tripBlogItemImageRelate) {
		this.tripBlogItemImageRelate = tripBlogItemImageRelate;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public String getEng_name() {
		return eng_name;
	}
	public void setEng_name(String eng_name) {
		this.eng_name = eng_name;
	}
	public String getCh_name() {
		return ch_name;
	}
	public void setCh_name(String ch_name) {
		this.ch_name = ch_name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTips() {
		return tips;
	}
	public void setTips(String tips) {
		this.tips = tips;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
