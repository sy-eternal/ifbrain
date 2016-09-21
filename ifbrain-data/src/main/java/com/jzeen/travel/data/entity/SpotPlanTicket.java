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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

@Entity
@Table(name = "t_spot_plan_ticket")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class SpotPlanTicket
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    //多对一门票类型及人数
    @ManyToOne
    @JoinColumn(name = "spot_plan_id", updatable = true)
    private SpotPlan spotPlan;

    // 门票类型
    @ManyToOne(targetEntity = SpotTicketType.class)
    @JoinColumn(name = "spot_ticket_type_id")
    private SpotTicketType spotTicketType;

    // 数量
    @Column(name = "person_count")
    private Integer personCount;

    // 销售价
    @Column(name = "sale_price")
    private BigDecimal salePrice;

    // 创建时间
    @Column(name = "create_time")
    private Date createTime;
    

    /*
     * 供应商订单号
     */
     @Column(name="supplier_num")
     private String supplierNum;



     public String getSupplierNum() {
			return supplierNum;
		}


		public void setSupplierNum(String supplierNum) {
			this.supplierNum = supplierNum;
		}
    
    

    public SpotTicketType getSpotTicketType()
    {
        return spotTicketType;
    }

    public void setSpotTicketType(SpotTicketType spotTicketType)
    {
        this.spotTicketType = spotTicketType;
    }

    public Integer getPersonCount()
    {
        return personCount;
    }

    public void setPersonCount(Integer personCount)
    {
        this.personCount = personCount;
    }

    public BigDecimal getSalePrice()
    {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice)
    {
        this.salePrice = salePrice;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public SpotPlan getSpotPlan()
    {
        return spotPlan;
    }

    public void setSpotPlan(SpotPlan spotPlan)
    {
        this.spotPlan = spotPlan;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }
}
