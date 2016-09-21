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
 * 日期规划
 */
@Entity
@Table(name = "t_date_plan")
// 解决延迟加载所造成的代理对象无法正常序列化的问题
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
// 解决双向关联的对象生成JSON的无限循环问题
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class DatePlan
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
//天数
    @Transient
    private String num;
    @ManyToOne(targetEntity = Order.class)
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Order order;

  //日序  
    @Column(name ="ordinated_day")
    private String ordinatedday;
  //
   // 日期
    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date date;
    
    
    @Column(name = "start_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date startDate;

    @Column(name = "end_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date endDate;

    @Column(name = "confirm_status")
    private Integer confirmStatus;

    @Size(max = 200)
    @Column(length = 200)
    private String feedback;

    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    
    
    @Column(name = "sub_total")
    private BigDecimal subTotal;
    
    
    
    @Transient
    private String dateString;
    
    @Transient
    private BigDecimal dayTotalAmount;
    
   
	@Transient
    private BigDecimal flighttotalAmount;
    
    @Transient
    private BigDecimal guidetotalAmount;
    
    @Transient 
    private BigDecimal rentaltotalAmount;
    
    @Transient
    private BigDecimal spottotalAmount;
    
    @Transient
    private BigDecimal hoteltalAmount;
    
    //备注
    
    @Column(name="remark")
    private String remark;
    
    @Transient
    private String dateDatePlans;
    
    public String getDateDatePlans() {
		return dateDatePlans;
	}

	public void setDateDatePlans(String dateDatePlans) {
		this.dateDatePlans = dateDatePlans;
	}

	public BigDecimal getFlighttotalAmount() {
		return flighttotalAmount;
	}

	public void setFlighttotalAmount(BigDecimal flighttotalAmount) {
		this.flighttotalAmount = flighttotalAmount;
	}

	public BigDecimal getGuidetotalAmount() {
		return guidetotalAmount;
	}

	public void setGuidetotalAmount(BigDecimal guidetotalAmount) {
		this.guidetotalAmount = guidetotalAmount;
	}

	public BigDecimal getRentaltotalAmount() {
		return rentaltotalAmount;
	}

	public void setRentaltotalAmount(BigDecimal rentaltotalAmount) {
		this.rentaltotalAmount = rentaltotalAmount;
	}

	public BigDecimal getSpottotalAmount() {
		return spottotalAmount;
	}

	public void setSpottotalAmount(BigDecimal spottotalAmount) {
		this.spottotalAmount = spottotalAmount;
	}

	public BigDecimal getHoteltalAmount() {
		return hoteltalAmount;
	}

	public void setHoteltalAmount(BigDecimal hoteltalAmount) {
		this.hoteltalAmount = hoteltalAmount;
	}

    
    
    public BigDecimal getSubTotal()
    {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal)
    {
        this.subTotal = subTotal;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public BigDecimal getDayTotalAmount()
    {
        return dayTotalAmount;
    }

    public void setDayTotalAmount(BigDecimal dayTotalAmount)
    {
        this.dayTotalAmount = dayTotalAmount;
    }

    public String getDateString()
    {
        return dateString;
    }

    public void setDateString(String dateString)
    {
        this.dateString = dateString;
    }

    public String getOrdinatedday()
    {
        return ordinatedday;
    }

    public void setOrdinatedday(String ordinatedday)
    {
        this.ordinatedday = ordinatedday;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Order getOrder()
    {
        return order;
    }

    public void setOrder(Order order)
    {
        this.order = order;
    }

    public Date getStartDate()
    {
        return startDate;
    }



    public String getNum()
    {
        return num;
    }

    public void setNum(String num)
    {
        this.num = num;
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

    public Integer getConfirmStatus()
    {
        return confirmStatus;
    }

    public void setConfirmStatus(Integer confirmStatus)
    {
        this.confirmStatus = confirmStatus;
    }

    public String getFeedback()
    {
        return feedback;
    }

    public void setFeedback(String feedback)
    {
        this.feedback = feedback;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    /**
     * 景点规划
     */
    @OneToMany(mappedBy = "datePlan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SpotPlan> spotPlan;

    public List<SpotPlan> getSpotPlan()
    {
        return spotPlan;
    }

    public void setSpotPlan(List<SpotPlan> spotPlan)
    {
        this.spotPlan = spotPlan;
    }

   

   

    /**
     * 酒店规划
     */
    @OneToMany(mappedBy = "datePlan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HotelPlan> hotelPlan;

    public List<HotelPlan> getHotelPlan()
    {
        return hotelPlan;
    }

    public void setHotelPlan(List<HotelPlan> hotelPlan)
    {
        this.hotelPlan = hotelPlan;
    }

    /**
     * 城市规划
     */
    @OneToOne(mappedBy = "datePlan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private CityPlan cityPlan;

    public CityPlan getCityPlan()
    {
        return cityPlan;
    }

    public void setCityPlan(CityPlan cityPlan)
    {
        this.cityPlan = cityPlan;
    }

    /**
     * 交通规划
     */
    @OneToOne(mappedBy = "datePlan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private VehiclePlan vehiclePlan;

    public VehiclePlan getVehiclePlan()
    {
        return vehiclePlan;
    }

    public void setVehiclePlan(VehiclePlan vehiclePlan)
    {
        this.vehiclePlan = vehiclePlan;
    }

    /**
     * 短途导游规划
     */
   @OneToMany(mappedBy = "datePlan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ExcursionGuidePlan> excursionGuidePlans;

    public List<ExcursionGuidePlan> getExcursionGuidePlans()
    {
        return excursionGuidePlans;
    }

    public void setExcursionGuidePlans(List<ExcursionGuidePlan> excursionGuidePlans)
    {
        this.excursionGuidePlans = excursionGuidePlans;
    }
    
    /**
     * 导游规划
     */
    @OneToMany(mappedBy = "datePlan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GuideActivityPlan> guideActivityPlans;

    public List<GuideActivityPlan> getGuideActivityPlans() {
        return guideActivityPlans;
    }

    public void setGuideActivityPlans(List<GuideActivityPlan> guideActivityPlans) {
        this.guideActivityPlans = guideActivityPlans;
    }

    /**
     * 标准导游规划
     */
    @OneToMany(mappedBy = "datePlan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StandardGuidePlan> standardGuidePlans;

    public List<StandardGuidePlan> getStandardGuidePlans()
    {
        return standardGuidePlans;
    }

    public void setStandardGuidePlans(List<StandardGuidePlan> standardGuidePlans)
    {
        this.standardGuidePlans = standardGuidePlans;
    }
    /**
     *租车 
     */
    @OneToMany(mappedBy = "datePlan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<RentalPlan> rentalPlan;

  
    //飞机规划
    @OneToMany(mappedBy = "datePlan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FilghtPlan> filghtPlan;

    
    
    
    
  

    public List<RentalPlan> getRentalPlan()
    {
        return rentalPlan;
    }

    public void setRentalPlan(List<RentalPlan> rentalPlan)
    {
        this.rentalPlan = rentalPlan;
    }

    public List<FilghtPlan> getFilghtPlan()
    {
        return filghtPlan;
    }

    public void setFilghtPlan(List<FilghtPlan> filghtPlan)
    {
        this.filghtPlan = filghtPlan;
    }
    @OneToMany(mappedBy = "datePlan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RentalPlan> specialCarPlan;

    public List<RentalPlan> getSpecialCarPlan()
    {
        return specialCarPlan;
    }

    public void setSpecialCarPlan(List<RentalPlan> specialCarPlan)
    {
        this.specialCarPlan = specialCarPlan;
    }

}
