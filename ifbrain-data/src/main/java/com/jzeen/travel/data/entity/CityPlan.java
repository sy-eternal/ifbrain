package com.jzeen.travel.data.entity;

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

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

/**
 * 城市规划
 */
@Entity
@Table(name = "t_city_plan")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class CityPlan
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne(targetEntity = DatePlan.class)
    @JoinColumn(name = "date_plan_id")
    @JsonBackReference
    private DatePlan datePlan;

    @ManyToOne(targetEntity = City.class)
    @JoinColumn(name = "from_city_id")
    private City fromCity;

    @ManyToOne(targetEntity = City.class)
    @JoinColumn(name = "to_city_id")
    private City toCity;

    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public DatePlan getDatePlan()
    {
        return datePlan;
    }

    public void setDatePlan(DatePlan datePlan)
    {
        this.datePlan = datePlan;
    }

    public City getFromCity()
    {
        return fromCity;
    }

    public void setFromCity(City fromCity)
    {
        this.fromCity = fromCity;
    }

    public City getToCity()
    {
        return toCity;
    }

    public void setToCity(City toCity)
    {
        this.toCity = toCity;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }
}
