package com.jzeen.travel.data.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

/**
 * 保险费率表
 */
@Entity
@Table(name = "t_insurance_rate")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class InsuranceRate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	//保险活动
	@ManyToOne(targetEntity = InsuranceActivity.class)
	@JoinColumn(name="insurace_activity_id",updatable = true)
	private InsuranceActivity  insuranceActivity;

	//成本价
	@Column(name="insurance_rate_cost")
	private BigDecimal insuranceRateCost;

	//销售价
	@Column(name="insurance_rate_price")
	private BigDecimal insuranceRatePrice;

	//保险期间
	@Column(name="insurance_duration")
	private String insuranceDuration;

	//保险类型
	@Column(name="holder_type")
	private String holderType;

	//创建时间
	@Column(name="update_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public InsuranceActivity getInsuranceActivity() {
		return insuranceActivity;
	}

	public void setInsuranceActivity(InsuranceActivity insuranceActivity) {
		this.insuranceActivity = insuranceActivity;
	}

	public BigDecimal getInsuranceRateCost() {
		return insuranceRateCost;
	}

	public void setInsuranceRateCost(BigDecimal insuranceRateCost) {
		this.insuranceRateCost = insuranceRateCost;
	}

	public BigDecimal getInsuranceRatePrice() {
		return insuranceRatePrice;
	}

	public void setInsuranceRatePrice(BigDecimal insuranceRatePrice) {
		this.insuranceRatePrice = insuranceRatePrice;
	}

	public String getInsuranceDuration() {
		return insuranceDuration;
	}

	public void setInsuranceDuration(String insuranceDuration) {
		this.insuranceDuration = insuranceDuration;
	}

	public String getHolderType() {
		return holderType;
	}

	public void setHolderType(String holderType) {
		this.holderType = holderType;
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
