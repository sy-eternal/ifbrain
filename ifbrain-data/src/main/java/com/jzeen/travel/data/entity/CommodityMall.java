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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

/*
 * 商品商城代码表
 */
@Entity
@Table(name = "t_commodity_mall")
@JsonIdentityInfo(generator = JSOGGenerator.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CommodityMall{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
//类型
	@Column(name = "commodity_type", length = 255)
    private String commodityType;
	// 创建时间
	@Column(name = "create_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd ")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date createTime;
	
	//一个商品类型对应多个商品
	@OneToMany(mappedBy = "commodityMall", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ShoppingmallCommodity> shoppingmallCommodity;
	
		
	@Column(name = "type", updatable = true)
	private String type;

	

	public List<ShoppingmallCommodity> getShoppingmallCommodity() {
		return shoppingmallCommodity;
	}

	public void setShoppingmallCommodity(
			List<ShoppingmallCommodity> shoppingmallCommodity) {
		this.shoppingmallCommodity = shoppingmallCommodity;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCommodityType() {
		return commodityType;
	}

	public void setCommodityType(String commodityType) {
		this.commodityType = commodityType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


}
