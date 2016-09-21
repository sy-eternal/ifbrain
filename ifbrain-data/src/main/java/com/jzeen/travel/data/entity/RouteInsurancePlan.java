package com.jzeen.travel.data.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

/**
 * 线路保险规划
 */
@Entity
@Table(name = "t_route_insurance_plan")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class RouteInsurancePlan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

	// 线路主键，
    @ManyToOne(targetEntity = Route.class)
    @JoinColumn(name = "route_id")
    private Route route;

	//保险活动主键
    @ManyToOne(targetEntity = InsuranceActivity.class)
    @JoinColumn(name="insurance_activity_id")
   private InsuranceActivity  insuranceActivity;
    
    //投保人数
    @Column(name="person_count")
    private Integer personCount;
    
    //险种
    @Column(name="insurance_name")
    private String insuranceName;
    
    //保险期间
    @Column(name="insurance_duration")
    private String insuranceDuration;
    
    //小计
    @Column(name="sub_total_amount")
    private BigDecimal subTotalAmount;
    
    //人员类型
    @Column(name="holder_type")
    private String holderType;
    
    @Column(name="create_time")
    private Date createTime;

    public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}
      
    public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getHolderType() {
		return holderType;
	}

	public void setHolderType(String holderType) {
		this.holderType = holderType;
	}

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

	public Integer getPersonCount() {
		return personCount;
	}

	public void setPersonCount(Integer personCount) {
		this.personCount = personCount;
	}

	public String getInsuranceName() {
		return insuranceName;
	}

	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}

	public String getInsuranceDuration() {
		return insuranceDuration;
	}

	public void setInsuranceDuration(String insuranceDuration) {
		this.insuranceDuration = insuranceDuration;
	}

	public BigDecimal getSubTotalAmount() {
		return subTotalAmount;
	}

	public void setSubTotalAmount(BigDecimal subTotalAmount) {
		this.subTotalAmount = subTotalAmount;
	}

    
}
