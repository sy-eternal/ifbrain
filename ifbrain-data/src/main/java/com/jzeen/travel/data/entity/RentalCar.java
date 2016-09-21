package com.jzeen.travel.data.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
//专车活动表
@Entity
@Table(name = "t_rental_car")
@JsonIdentityInfo(generator = JSOGGenerator.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RentalCar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
//专车类型
    @Column(name = "car_type", length = 10)
    private String cartype;
//专车介绍
    @Column(name = "description")
    private String count;
    //更新时间
    @Column(name = "update_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;
    
    /**
     * 供应商为主键，一对多关系
     */
    @ManyToOne(targetEntity = Supplier.class)
    @JoinColumn(name="supplier_id",updatable=true)
    private Supplier supplier;
    
    
    @OneToMany(mappedBy = "specialcar", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RentalRate> specialCarRate;
    
    
    @OneToMany(mappedBy = "specialcar", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RentalPlan> SpecialCarPlan;
    


    public List<RentalPlan> getSpecialCarPlan()
    {
        return SpecialCarPlan;
    }

    public void setSpecialCarPlan(List<RentalPlan> specialCarPlan)
    {
        SpecialCarPlan = specialCarPlan;
    }

    public List<RentalRate> getSpecialCarRate()
    {
        return specialCarRate;
    }

    public void setSpecialCarRate(List<RentalRate> specialCarRate)
    {
        this.specialCarRate = specialCarRate;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }


    public String getCartype()
    {
        return cartype;
    }

    public void setCartype(String cartype)
    {
        this.cartype = cartype;
    }

    public String getCount()
    {
        return count;
    }

    public void setCount(String count)
    {
        this.count = count;
    }

    public Date getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }

    public Supplier getSupplier()
    {
        return supplier;
    }

    public void setSupplier(Supplier supplier)
    {
        this.supplier = supplier;
    }

    


   
}
