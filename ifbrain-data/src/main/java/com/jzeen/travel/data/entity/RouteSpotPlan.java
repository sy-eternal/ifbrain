package com.jzeen.travel.data.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

/**
 * 线路景点规划
 */
@Entity
@Table(name = "t_route_spot_plan")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class RouteSpotPlan {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	//线路规划主键
	@ManyToOne(targetEntity = RouteDays.class)
	@JoinColumn(name="route_day_id")
	private RouteDays routeDays;  

	public RouteDays getRouteDays() {
		return routeDays;
	}

	public void setRouteDays(RouteDays routeDays) {
		this.routeDays = routeDays;
	}

	// 供应商订单号
	@Column(name = "spot_provider_order")
	private String supplierNum;

	// 景点主键 一对多的关系
	@ManyToOne(targetEntity = Spot.class)
	@JoinColumn(name = "spot_pk")
	private Spot spot;

	// 景点备注
	private String remark;

	// 创建时间
	@Column(name = "create_time")
	private Date createTime;

	// 门票类型
	@OneToMany(mappedBy = "routeSpotPlan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<RouteSpotPlanTicket> routeSpotPlanTickets;

		//小计金额
    @Column(name="subtotal_amount ")
    private BigDecimal subtotalAmount ;

	public void setRouteSpotPlanTickets(
			List<RouteSpotPlanTicket> routeSpotPlanTickets) {
		this.routeSpotPlanTickets = routeSpotPlanTickets;
	}
	
	public BigDecimal getSubtotalAmount() {
		return subtotalAmount;
	}

	public void setSubtotalAmount(BigDecimal subtotalAmount) {
		this.subtotalAmount = subtotalAmount;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSupplierNum() {
		return supplierNum;
	}

	public void setSupplierNum(String supplierNum) {
		this.supplierNum = supplierNum;
	}

	public Spot getSpot() {
		return spot;
	}

	public void setSpot(Spot spot) {
		this.spot = spot;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<RouteSpotPlanTicket> getRouteSpotPlanTickets() {
		return routeSpotPlanTickets;
	}

}
