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

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

/*
 * 
 * 计划购买的商品表
 */
@Entity
@Table(name = "t_buy_plan")
@JsonIdentityInfo(generator = JSOGGenerator.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BuyPlan {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	
	@Column(name = "name")
	private String name;

	@OneToOne(targetEntity = CommodityType.class)
	@JoinColumn(name = "commodity_type_id", updatable = true)
	private CommodityType commodityType;

	@Column(name = "price")
	private BigDecimal price;

/*	@OneToOne(targetEntity = Image.class)
	@JoinColumn(name = "picture_id", updatable = true)
	private Image image;*/
	
	
	@ManyToOne(targetEntity = Child.class)
	@JoinColumn(name = "t_child_id", updatable = true)
	private Child child;
	
	@Column(name = "update_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd ")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date updateTime;
    
	@Column(name = "plan_status")
	private Integer planStatus;
	
	
	
	public Date getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	public Integer getPlanStatus() {
		return planStatus;
	}


	public void setPlanStatus(Integer planStatus) {
		this.planStatus = planStatus;
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


	public CommodityType getCommodityType() {
		return commodityType;
	}


	public void setCommodityType(CommodityType commodityType) {
		this.commodityType = commodityType;
	}


	public BigDecimal getPrice() {
		return price;
	}


	public void setPrice(BigDecimal price) {
		this.price = price;
	}


/*	public Image getImage() {
		return image;
	}


	public void setImage(Image image) {
		this.image = image;
	}*/


	public Child getChild() {
		return child;
	}


	public void setChild(Child child) {
		this.child = child;
	}


	

}