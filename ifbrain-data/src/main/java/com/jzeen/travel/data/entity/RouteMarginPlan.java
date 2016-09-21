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
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

@Entity
@Table(name = "t_route_margin_plan")
@JsonIdentityInfo(generator = JSOGGenerator.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RouteMarginPlan {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	//线路主键
	@ManyToOne(targetEntity = Route.class)
	@JoinColumn(name = "route_id")
	@JsonBackReference
	private Route route;

	//保证金主键
	@ManyToOne(targetEntity = Margin.class)
	@JoinColumn(name = "margin_id", updatable = true)
	private Margin margin;

	//价格
	@Column(name = "margin_price")
	private BigDecimal  marginprice;

	//创建时间
	@Column(name = "create_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd ")
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	private Date createTime;

	//数量
	@Column(name = "count", length = 255)
	private int count;

	//总价

	@Column(name = "total_margin_price") 
	private BigDecimal margintotalprice;

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public BigDecimal getMargintotalprice()
	{
		return margintotalprice;
	}

	public void setMargintotalprice(BigDecimal margintotalprice)
	{
		this.margintotalprice = margintotalprice;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Margin getMargin()
	{
		return margin;
	}

	public void setMargin(Margin margin)
	{
		this.margin = margin;
	}



	public BigDecimal getMarginprice()
	{
		return marginprice;
	}

	public void setMarginprice(BigDecimal marginprice)
	{
		this.marginprice = marginprice;
	}

	public Date getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}

	public int getCount()
	{
		return count;
	}

	public void setCount(int count)
	{
		this.count = count;
	}
}
