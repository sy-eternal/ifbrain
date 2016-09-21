package com.jzeen.travel.data.entity;

import java.math.BigDecimal;
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
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

// 导游类型规划
@Entity
@Table(name="t_guide_type_plan")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class GuideTypePlan {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	/**
	 * 导游活动规划主键
	 */
	@ManyToOne(targetEntity=GuideActivityPlan.class)
	@JoinColumn(name="guide_activity_plan_id")
	private GuideActivityPlan guideActivityPlan;
	
	/**
	 * 导游类型规划关联表
	 */
	@OneToMany(mappedBy = "guideTypePlan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GuideGuidetypePlanRelate> guideGuidetypePlanRelate;
	
	public List<GuideGuidetypePlanRelate> getGuideGuidetypePlanRelate() {
		return guideGuidetypePlanRelate;
	}

	public void setGuideGuidetypePlanRelate(
			List<GuideGuidetypePlanRelate> guideGuidetypePlanRelate) {
		this.guideGuidetypePlanRelate = guideGuidetypePlanRelate;
	}

	/**
	 * 导游费率主键
	 */
	@ManyToOne(targetEntity=GuideRate.class)
	@JoinColumn(name="guide_rate_id")
	private GuideRate guideRate;
	
	/**
	 * 导游类型
	 */
	@Column(name="guide_type")
	private String guideType;
	
	/**
	 * 导游数量
	 */
	@Column(name="guide_count")
	private Integer guideCount;
	
	/**
	 * 导游成本美元
	 */
	@Column(name="guide_cost")
	private BigDecimal guideCost;
	
	/**
	 * 导游销售价
	 */
	@Column(name="guide_price")
	private BigDecimal guidePrice;
	/**
	 * 派单状态
	 */
	@Column(name="appointed_status")
	private Integer appointedStatus;
	public Integer getAppointedStatus() {
		return appointedStatus;
	}

	public void setAppointedStatus(Integer appointedStatus) {
		this.appointedStatus = appointedStatus;
	}

	/**
	 * update time
	 */
	@Column(name="update_time")
	private Date updateTime;
	
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

	public GuideActivityPlan getGuideActivityPlan() {
		return guideActivityPlan;
	}

	public void setGuideActivityPlan(GuideActivityPlan guideActivityPlan) {
		this.guideActivityPlan = guideActivityPlan;
	}

	public GuideRate getGuideRate() {
		return guideRate;
	}

	public void setGuideRate(GuideRate guideRate) {
		this.guideRate = guideRate;
	}

	public String getGuideType() {
		return guideType;
	}

	public void setGuideType(String guideType) {
		this.guideType = guideType;
	}

	public Integer getGuideCount()
    {
        return guideCount;
    }

    public void setGuideCount(Integer guideCount)
    {
        this.guideCount = guideCount;
    }

    public BigDecimal getGuideCost() {
		return guideCost;
	}

	public void setGuideCost(BigDecimal guideCost) {
		this.guideCost = guideCost;
	}

	public BigDecimal getGuidePrice() {
		return guidePrice;
	}

	public void setGuidePrice(BigDecimal guidePrice) {
		this.guidePrice = guidePrice;
	}

    public Date getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }
}
