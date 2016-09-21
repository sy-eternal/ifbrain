package com.jzeen.travel.data.entity;


import java.math.BigDecimal;
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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

/*
 * 儿童兑换商品表
 */
@Entity
@Table(name = "t_shop_history")
@JsonIdentityInfo(generator = JSOGGenerator.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ShopHistory {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	
	@ManyToOne(targetEntity = Child.class)
	@JoinColumn(name = "t_child_id", updatable = true)
	private Child child;
	
	@Column(name="start_time")
	private Date startTime;
	
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "t_user_id", updatable = true)
	private User user;
	
	@Column(name="name")
	private String name;

	@Column(name="price")
	private BigDecimal price;
	
	@OneToOne(targetEntity = Image.class)
	@JoinColumn(name = "picture", updatable = true)
	private Image image;
	
	@OneToOne(targetEntity = CommodityType.class)
	@JoinColumn(name = "commodity_type_id", updatable = true)
	private CommodityType commodityType;
	
	@Column(name="buy_number")
	private Integer buyNumber;
	
	public Integer getBuyNumber() {
		return buyNumber;
	}

	public void setBuyNumber(Integer buyNumber) {
		this.buyNumber = buyNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public CommodityType getCommodityType() {
		return commodityType;
	}

	public void setCommodityType(CommodityType commodityType) {
		this.commodityType = commodityType;
	}
	
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Child getChild() {
		return child;
	}

	public void setChild(Child child) {
		this.child = child;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date date) {
		this.startTime = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
