package com.jzeen.travel.data.entity;

import java.math.BigDecimal;
import java.security.Timestamp;
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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;


@Entity
@Table(name ="t_guide_rate")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class GuideRate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	/**
	 * 导游活动id
	 */
	@ManyToOne (targetEntity=GuideActivity.class)
	@JoinColumn(name="guide_activity_id", updatable =true)
	private GuideActivity guideActivity;
	
	/**
	 * 导游类型
	 */
	@Column(name="guide_type")
	private String guideType;
	
	/**
	 * 导游成本美元
	 */
	@Column(name="guide_rate_cost")
	private BigDecimal guideRateCost;
	
	/**
	 * 导游销售价格
	 */
	@Column(name="guide_rate_price")
	private BigDecimal guideRatePrice;
	
	/**
	 *  更新时间
	 */
	
	@Column(name="update_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public GuideActivity getGuideActivity() {
		return guideActivity;
	}

	public void setGuideActivity(GuideActivity guideActivity) {
		this.guideActivity = guideActivity;
	}

	public BigDecimal getGuideRateCost() {
		return guideRateCost;
	}

	public void setGuideRateCost(BigDecimal guideRateCost) {
		this.guideRateCost = guideRateCost;
	}

	public BigDecimal getGuideRatePrice() {
		return guideRatePrice;
	}

	public void setGuideRatePrice(BigDecimal guideRatePrice) {
		this.guideRatePrice = guideRatePrice;
	}

	

	public Date getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }

    public String getGuideType() {
		return guideType;
	}

	public void setGuideType(String guideType) {
		this.guideType = guideType;
	}
	
	
	
}
