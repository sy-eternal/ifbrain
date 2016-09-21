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

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

// 酒店规划
@Entity
@Table(name = "t_hotel_plan")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class HotelPlan
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    /**
     * 日期规划
     */
    @ManyToOne(targetEntity = DatePlan.class)
    @JoinColumn(name = "date_plan_id")
    private DatePlan datePlan;
    public DatePlan getDatePlan()
    {
        return datePlan;
    }

    public void setDatePlan(DatePlan datePlan)
    {
        this.datePlan = datePlan;
    }

    /**
     * 酒店活动主键
     */
    @ManyToOne(targetEntity = HotelActivity.class)
    @JoinColumn(name = "hotel_id", updatable = true)
    private HotelActivity hotelActivity;
    public HotelActivity getHotelActivity()
    {
        return hotelActivity;
    }

    public void setHotelActivity(HotelActivity hotelActivity)
    {
        this.hotelActivity = hotelActivity;
    }

    /**
     * 目的城市
     */
    @ManyToOne(targetEntity = City.class)
    @JoinColumn(name = "city_id")
    private City city;
    public City getCity()
    {
        return city;
    }

    public void setCity(City city)
    {
        this.city = city;
    }

    /**
     * 入住日期
     */
    @Column(name="check_in_date")
   /* @DateTimeFormat(pattern = "yyyy-MM-dd ")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")*/
    private String checkInDate;
   

    public String getCheckInDate()
    {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate)
    {
        this.checkInDate = checkInDate;
    }

    /**
     * 退房日期
     */
    @Column(name="check_out_date")
   /* @DateTimeFormat(pattern = "yyyy-MM-dd ")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")*/
    private String checkOutDate;
    
  /*  public Date getCheckOutDate()
    {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate)
    {
        this.checkOutDate = checkOutDate;
    }*/
    
    public String getCheckOutDate()
    {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate)
    {
        this.checkOutDate = checkOutDate;
    }

    /**
     * 酒店英文名
     */
    @Column(name="hotel_eng_name")
    private String hotelEngName;
    public String getHotelEngName()
    {
        return hotelEngName;
    }

    public void setHotelEngName(String hotelEngName)
    {
        this.hotelEngName = hotelEngName;
    }
    
 // 房间类型规划
    @OneToMany(mappedBy = "hotelPlan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HotelPlanRoom> hotelPlanRooms;
    
    public List<HotelPlanRoom> getHotelPlanRooms() {
		return hotelPlanRooms;
	}

	public void setHotelPlanRooms(List<HotelPlanRoom> hotelPlanRooms) {
		this.hotelPlanRooms = hotelPlanRooms;
	}

	/**
     * 酒店中文名
     */
    @Column(name="hotel_cha_name")
    private String hotelChaName;
    public String getHotelChaName()
    {
        return hotelChaName;
    }

    public void setHotelChaName(String hotelChaName)
    {
        this.hotelChaName = hotelChaName;
    }
    
    /**
     * 小计金额
     */
    @Column(name="subtotal_amount")
    private BigDecimal subtotalAmount;
    public BigDecimal getSubtotalAmount()
    {
        return subtotalAmount;
    }

    public void setSubtotalAmount(BigDecimal subtotalAmount)
    {
        this.subtotalAmount = subtotalAmount;
    }
    
    /**
     * 创建时间
     */
    @Column(name="create_time")
    private Date createTime;

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }
  
    
    
  
}
