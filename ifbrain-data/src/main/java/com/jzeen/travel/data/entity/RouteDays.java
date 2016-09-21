package com.jzeen.travel.data.entity;

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
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

import java.math.BigDecimal;
/**
 * 线路日期规划
 */
@Entity
@Table(name = "t_route_days")
// 解决延迟加载所造成的代理对象无法正常序列化的问题
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
// 解决双向关联的对象生成JSON的无限循环问题
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class RouteDays
{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @ManyToOne(targetEntity = Route.class)
    @JoinColumn(name = "route_id")
    @JsonBackReference
    private Route route;

  //日序  
    @Column(name ="day_id")
    private Integer dayId;
    
  //
    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    
    /**
     * 景点规划
     */
    @OneToMany(mappedBy = "routeDays", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RouteSpotPlan> spotPlan;

    public List<RouteSpotPlan> getSpotPlan()
    {
        return spotPlan;
    }

    public void setSpotPlan(List<RouteSpotPlan> spotPlan)
    {
        this.spotPlan = spotPlan;
    }     

    /**
     * 酒店规划
     */
    @OneToMany(mappedBy = "routeDays", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RouteHotelPlan> hotelPlan;

    public List<RouteHotelPlan> getHotelPlan()
    {
        return hotelPlan;
    }

    public void setHotelPlan(List<RouteHotelPlan> hotelPlan)
    {
        this.hotelPlan = hotelPlan;
    }

    /**
     * 导游规划
     */
    @OneToMany(mappedBy = "routeDays", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RouteGuideActivityPlan> guideActivityPlans;

    public List<RouteGuideActivityPlan> getGuideActivityPlans() {
        return guideActivityPlans;
    }

    public void setGuideActivityPlans(List<RouteGuideActivityPlan> guideActivityPlans) {
        this.guideActivityPlans = guideActivityPlans;
    }

    /**
     *租车 
     */
    @OneToMany(mappedBy = "routeDays", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<RouteRentalPlan> rentalPlan;

    public List<RouteRentalPlan> getRentalPlan()
    {
        return rentalPlan;
    }

    public void setRentalPlan(List<RouteRentalPlan> rentalPlan)
    {
        this.rentalPlan = rentalPlan;
    }

  
    //飞机规划
    @OneToMany(mappedBy = "routeDays", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RouteFilghtPlan> filghtPlan;

    public List<RouteFilghtPlan> getFilghtPlan()
    {
        return filghtPlan;
    }

    public void setFilghtPlan(List<RouteFilghtPlan> filghtPlan)
    {
        this.filghtPlan = filghtPlan;
    }
    

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public Integer getDayId() {
		return dayId;
	}

	public void setDayId(Integer dayId) {
		this.dayId = dayId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


}
