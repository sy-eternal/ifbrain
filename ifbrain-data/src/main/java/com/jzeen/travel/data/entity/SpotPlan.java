package com.jzeen.travel.data.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

/**
 * 景点规划
 */
@Entity
@Table(name = "t_spot_plan")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class SpotPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    // 日期规划主键，一对一关系
    @ManyToOne(targetEntity = DatePlan.class)
    @JoinColumn(name = "date_plan_pk")
    
    private DatePlan datePlan;

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
    @OneToMany(mappedBy = "spotPlan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SpotPlanTicket> spotPlanTicket;



	//小计金额
    @Column(name="subtotal_amount ")
    private BigDecimal subtotalAmount ;
    
    
    /*
     * 供应商订单号
     */


     public String getSupplierNum() {
			return supplierNum;
		}


		public void setSupplierNum(String supplierNum) {
			this.supplierNum = supplierNum;
		}

    public BigDecimal getSubtotalAmount() {
		return subtotalAmount;
	}

	public void setSubtotalAmount(BigDecimal subtotalAmount) {
		this.subtotalAmount = subtotalAmount;
	}

    public List<SpotPlanTicket> getSpotPlanTicket()
    {
        return spotPlanTicket;
    }

    public void setSpotPlanTicket(List<SpotPlanTicket> spotPlanTicket)
    {
        this.spotPlanTicket = spotPlanTicket;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DatePlan getDatePlan() {
        return datePlan;
    }

    public void setDatePlan(DatePlan datePlan) {
        this.datePlan = datePlan;
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
}
