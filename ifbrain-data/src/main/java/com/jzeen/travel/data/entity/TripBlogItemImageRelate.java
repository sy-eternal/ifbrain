package com.jzeen.travel.data.entity;

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
@Table(name = "t_tripblogitem_image_relate")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class TripBlogItemImageRelate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	//游记条目主键
	@ManyToOne(targetEntity = TripBlogItem.class)
	@JoinColumn(name = "tripblog_item_id")
	private TripBlogItem tripblogItem;
	//图片主键
	@OneToOne(targetEntity=Image.class)
    @JoinColumn(name="image_id")
	private Image image;
	//创建时间
	@Column(name = "create_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date createTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public TripBlogItem getTripblogItem() {
		return tripblogItem;
	}
	public void setTripblogItem(TripBlogItem tripblogItem) {
		this.tripblogItem = tripblogItem;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
