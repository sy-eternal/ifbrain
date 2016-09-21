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

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
//专车规划表
@Entity
@Table(name = "t_rental_car_rate")
@JsonIdentityInfo(generator = JSOGGenerator.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RentalRate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
//专车活动id
    @ManyToOne(targetEntity = RentalCar.class)
    @JoinColumn(name = "special_car_id")
    private RentalCar specialcar;
//专车成本
    @Column(name = "car_rate_cost", length = 10)
    private BigDecimal carratecost;
//专车销售价格
    @Column(name = "car_rate_price")
    private BigDecimal carrateprice;
    //更新时间 
    @Column(name = "update_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }
    public RentalCar getSpecialcar()
    {
        return specialcar;
    }

    public void setSpecialcar(RentalCar specialcar)
    {
        this.specialcar = specialcar;
    }

    public BigDecimal getCarratecost()
    {
        return carratecost;
    }

    public void setCarratecost(BigDecimal carratecost)
    {
        this.carratecost = carratecost;
    }

    public BigDecimal getCarrateprice()
    {
        return carrateprice;
    }

    public void setCarrateprice(BigDecimal carrateprice)
    {
        this.carrateprice = carrateprice;
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
