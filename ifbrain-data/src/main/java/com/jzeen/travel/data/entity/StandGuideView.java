package com.jzeen.travel.data.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "standguideview4")
public class StandGuideView
{
    @Id
    @Column(name = "id")
    private Integer id;
   
    @Column(name = "order_id")
    private Integer orderId;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @Column(name = "start_date")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "guide_count")
    private Integer guideCount;

    @Column(name = "guide_price")
    private BigDecimal guidePrice;

    @Column(name = "cn_name")
    private String cnName;

   @Column(name = "guide_pk")
    private Integer guidePk;

   @Column(name = "guide_car_price")
   private String guideCarPrice;
   
   @Column(name = "commision_percentage")
   private String commisionPercentage;
   
   
    public Integer getOrderId()
{
    return orderId;
}

public void setOrderId(Integer orderId)
{
    this.orderId = orderId;
}

    public String getGuideCarPrice()
    {
        return guideCarPrice;
    }

    public void setGuideCarPrice(String guideCarPrice)
    {
        this.guideCarPrice = guideCarPrice;
    }

    public String getCommisionPercentage()
    {
        return commisionPercentage;
    }

    public void setCommisionPercentage(String commisionPercentage)
    {
        this.commisionPercentage = commisionPercentage;
    }

    public Integer getGuidePk()
    {
        return guidePk;
    }

    public void setGuidePk(Integer guidePk)
    {
        this.guidePk = guidePk;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Date getStartDate()
    {
        return startDate;
    }

    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    public Integer getGuideCount()
    {
        return guideCount;
    }

    public void setGuideCount(Integer guideCount)
    {
        this.guideCount = guideCount;
    }

    public BigDecimal getGuidePrice()
    {
        return guidePrice;
    }

    public void setGuidePrice(BigDecimal guidePrice)
    {
        this.guidePrice = guidePrice;
    }

    public String getCnName()
    {
        return cnName;
    }

    public void setCnName(String cnName)
    {
        this.cnName = cnName;
    }

}
