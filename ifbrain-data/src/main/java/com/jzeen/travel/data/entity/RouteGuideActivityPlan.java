package com.jzeen.travel.data.entity;

import java.math.BigDecimal;
import java.security.Timestamp;
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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

//线路导游活动类型规划

@Entity
@Table (name="t_route_guide_activity_plan")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class RouteGuideActivityPlan {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
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
	
	/**
	 * 导游活动id
	 */
	@ManyToOne(targetEntity=GuideActivity.class)
	@JoinColumn(name="guide_activity_id")
	private GuideActivity guideActity;
	
	/**
	 * 城市id
	 */
	@OneToOne(targetEntity=City.class)
    @JoinColumn(name="city_id")
	private City city;
	
	/**
	 * 导游人数
	 */
	@Column(name="guide_count")
	private Integer guideCount;
	
	/**
	 * 导游活动类型
	 */
	@Column(name="guide_activity_type")
	private String guideActivityType;
	
	/**
	 * 小计
	 */
	@Column(name="sub_total_amount")
	private BigDecimal subTotalAmount;
	
	/**
	 * 更新时间
	 */
	@Column(name="update_time")
	private Date updateTime;
	
	@OneToMany(mappedBy = "routeGuideActivityPlan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RouteGuideTypePlan> routeGuideTypePlans;
	 
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public GuideActivity getGuideActity() {
		return guideActity;
	}

	public void setGuideActity(GuideActivity guideActity) {
		this.guideActity = guideActity;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Integer getGuideCount() {
		return guideCount;
	}

	public void setGuideCount(Integer guideCount) {
		this.guideCount = guideCount;
	}

	public String getGuideActivityType() {
		return guideActivityType;
	}

	public void setGuideActivityType(String guideActivityType) {
		this.guideActivityType = guideActivityType;
	}

	public BigDecimal getSubTotalAmount() {
		return subTotalAmount;
	}

	public void setSubTotalAmount(BigDecimal subTotalAmount) {
		this.subTotalAmount = subTotalAmount;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<RouteGuideTypePlan> getRouteGuideTypePlans() {
		return routeGuideTypePlans;
	}

	public void setRouteGuideTypePlans(List<RouteGuideTypePlan> routeGuideTypePlans) {
		this.routeGuideTypePlans = routeGuideTypePlans;
	}

}
