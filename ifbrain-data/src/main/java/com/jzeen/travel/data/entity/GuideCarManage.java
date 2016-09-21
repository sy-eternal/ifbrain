package com.jzeen.travel.data.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;

// 导游专车管理
@Entity
@Table(name = "t_guide_car_management")
public class GuideCarManage
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "create_time")
    private Date createTime;

    // 导游专车级别
    @Column(name = "class")
    private String classes;

    // 成本价
    @Min(0)
    @Column(name = "cost")
    private BigDecimal cost;

    // 销售价
    @Min(0)
    @Column(name = "sales_price")
    private BigDecimal salesPrice;

    // 短途专车成本
    @Min(0)
    @Column(name = "excursion_cost")
    private BigDecimal excursionCost;

    // 短途专车销售价
    @Min(0)
    @Column(name = "excursion_price")
    private BigDecimal excursionPrice;

    public BigDecimal getExcursionCost()
    {
        return excursionCost;
    }

    public void setExcursionCost(BigDecimal excursionCost)
    {
        this.excursionCost = excursionCost;
    }

    public BigDecimal getExcursionPrice()
    {
        return excursionPrice;
    }

    public void setExcursionPrice(BigDecimal excursionPrice)
    {
        this.excursionPrice = excursionPrice;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public String getClasses()
    {
        return classes;
    }

    public void setClasses(String classes)
    {
        this.classes = classes;
    }

    public BigDecimal getCost()
    {
        return cost;
    }

    public void setCost(BigDecimal cost)
    {
        this.cost = cost;
    }

    public BigDecimal getSalesPrice()
    {
        return salesPrice;
    }

    public void setSalesPrice(BigDecimal salesPrice)
    {
        this.salesPrice = salesPrice;
    }
}
