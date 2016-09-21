package com.jzeen.travel.data.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name = "orderview3")
public class GuideOutstandingOrders
{
    @Id
    @Column(name="id")
    private Integer id;
    
    @Column(name="order_number")
    private String orderNumber;
    
    
    
    @Column(name="start_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate; 
    
    
    @Column(name="end_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate; 
    
    @Column(name="first_name")
    private String firstName; 
    
    @Column(name="last_name")
    private String lastName; 
    
    @Column(name="guide_pk")
    private Integer guidePk;
   @Column(name="order_status")
    private Integer orderStatus;
    
   
   
    public Integer getOrderStatus()
    {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus)
    {
        this.orderStatus = orderStatus;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getOrderNumber()
    {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber)
    {
        this.orderNumber = orderNumber;
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

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public Integer getGuidePk()
    {
        return guidePk;
    }

    public void setGuidePk(Integer guidePk)
    {
        this.guidePk = guidePk;
    }
    
    
    

}
