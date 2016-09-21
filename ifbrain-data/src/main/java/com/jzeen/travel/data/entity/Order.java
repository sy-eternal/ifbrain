package com.jzeen.travel.data.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jzeen.travel.core.util.DateUtil;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 旅行订单
 */
@Entity
@Table(name = "t_order")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 游客
     */
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "traveler_id", updatable = true)
    private User traveler;

    /**
     * 订单号
     */
    @Size(max = 40)
    @Column(name = "order_number", length = 40)
    private String orderNumber;

    /**
     * 日期明确
     */
    @Column(name = "date_confirm")
    private Integer dateConfirm;

    /**
     * 开始日期
     */
    @Column(name = "start_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date startDate;

    /**
     * 结束日期
     */
    @Column(name = "end_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endDate;

    /**
     * 保险规划
     */
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<InsurancePlan> insurancePlan;
    
    
    //保险金价格
    @Transient
    private BigDecimal margintalAmount;
    /*
     * 是否显示每天的价格和价格详情
     */
    @Column(name = "amount_details_status")
    private Integer amountSetailStatus;
    
//汇率
    @Column(name="exchange_rate")
    private BigDecimal exchangerate;
    
    /*
     * 出行人员
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ConfiremedTraveler> confiremedTravelers;
    
    
    
    @Transient
    private String hasSent;
    
    /*
     * 暂定套餐名称
     */
    @Transient 
    private Integer packageId;
    
 

    public Integer getPackageId() {
		return packageId;
	}


	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}


	public String getHasSent() {
		return hasSent;
	}


	public void setHasSent(String hasSent) {
		this.hasSent = hasSent;
	}


	public List<ConfiremedTraveler> getConfiremedTravelers() {
		return confiremedTravelers;
	}


	public void setConfiremedTravelers(List<ConfiremedTraveler> confiremedTravelers) {
		this.confiremedTravelers = confiremedTravelers;
	}


	public BigDecimal getExchangerate()
    {
        return exchangerate;
    }


    public void setExchangerate(BigDecimal exchangerate)
    {
        this.exchangerate = exchangerate;
    }


    public Integer getAmountSetailStatus()
    {
        return amountSetailStatus;
    }


    public void setAmountSetailStatus(Integer amountSetailStatus)
    {
        this.amountSetailStatus = amountSetailStatus;
    }












    //dateplan
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DatePlan> datePlan;
    /**
     * 游玩天数da
     */
    private Integer duration;

    public List<DatePlan> getDatePlan()
    {
        return datePlan;
    }


    public void setDatePlan(List<DatePlan> datePlan)
    {
        this.datePlan = datePlan;
    }

    
    
    
    
    
    public BigDecimal getMargintalAmount()
    {
        return margintalAmount;
    }


    public void setMargintalAmount(BigDecimal margintalAmount)
    {
        this.margintalAmount = margintalAmount;
    }


    public List<InsurancePlan> getInsurancePlan()
    {
        return insurancePlan;
    }


    public void setInsurancePlan(List<InsurancePlan> insurancePlan)
    {
        this.insurancePlan = insurancePlan;
    }






    

/*
 * 订单规划表
 */
    @OneToOne(mappedBy = "order",targetEntity=PlanOrder.class)
    private PlanOrder planOrder;


    public PlanOrder getPlanOrder() {
		return planOrder;
	}


	public void setPlanOrder(PlanOrder planOrder) {
		this.planOrder = planOrder;
	}












	/**
     * 出行人数
     */
    @Column(name = "person_count")
    private Integer personCount;

    /**
     * 酒店房间数
     */
    @Column(name = "hotel_room_count")
    private Integer hotelRoomCount;

    /**
     * 国家
     */
    @ManyToOne(targetEntity = Country.class)
    @JoinColumn(name = "country_id", updatable = true)
    private Country country;

    /**
     * 出发城市
     */
    @ManyToOne(targetEntity = City.class)
    @JoinColumn(name = "start_city_id", updatable = true)
    private City startCity;

    /**
     * 返回城市
     */
    @ManyToOne(targetEntity = City.class)
    @JoinColumn(name = "back_city_id", updatable = true)
    private City backCity;

    /**
    * 结算总金额
    */
    
    @Column(name = "check_out_amount")
    private BigDecimal checkOutAmount;
    
	/**
     * 必去城市状态
     */
    @Column(name = "confirmed_city_status")
    private Integer confirmedCityStatus;

    /**
     * 兴趣城市状态
     */
    @Column(name = "interested_city_status")
    private Integer interestedCityStatus;

    /**
     * 订单状态
     */
    @Column(name = "order_status")
    private Integer orderStatus;

    /**
     * 订单备注
     */
    private String remark;

    /**
     * 订单总金额
     */
    @Column(name = "order_amount")
    private BigDecimal orderAmount;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;


    /**
     * 状态时间
     */
    @Column(name = "status_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date statusTime;

    /**
     * 提交客户时间
     */
    @Column(name = "commit_order_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date commitOrderTime;

    /**
     * 邀请码
     */
    @Column(name = "activity_code")
    private String activityCode;

    /**
     * 邀请码状态
     */
    @Column(name = "activity_status")
    private Integer activityStatus;

    /**
     * 剩余时间
     */
    @Transient
    public String getTimeRemain() {
        long minutes = 24 * 60 + (createTime.getTime() - new Date().getTime()) / (1000 * 60);
        if (minutes < 0) {
            return "0小时0分钟";
        }
        long hourRemain = minutes / 60;
        long minuteRemain = minutes - hourRemain * 60;
        return hourRemain + "小时" + minuteRemain + "分钟";
    }

    /**
     * 内部交通方式
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<InnerCityTrafficRelate> innerCityTrafficRelates;


    /**
     * 主题关联
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ThemeRelate> themeRelates;

    /**
     * 出行目的关联
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PurposeRelate> purposeRelates;

    /**
     * 必去城市
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CityConfirmed> cityConfirmeds;

    /**
     * 兴趣城市
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<InterestedCityRelate> interestedCityRelates;

    /**
     * 兴趣城市
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AccompanyRelate> accompanyRelates;

    /**
     * 兴趣城市
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AccompanyMemberAge> accompanyMemberAge;


    public List<AccompanyMemberAge> getAccompanyMemberAge() {
        return accompanyMemberAge;
    }


    /**
     * 出行目的
     *
     * @return 出行目的
     */
    @Transient
    public String getPurposes() {
        String purposes = "";
        for (PurposeRelate purposeRelate : purposeRelates) {
            if (purposeRelate.getPurpose() != null) {
                purposes += ", " + purposeRelate.getPurposeName();
            }
        }

        return purposes.length() > 0 ? purposes.substring(2) : "";
    }


    /**
     * 人员年龄
     *
     * @return
     */
    @Transient
    public String setAccompanyMemberAge(List<AccompanyMemberAge> accompanyMemberAge) {
        String memberage = "";
        for (AccompanyMemberAge accompanyage : accompanyMemberAge) {
            if (accompanyage.getId() != null) {
                memberage += ", " + accompanyage.getAccompanymemberage();
            }
        }
        return memberage.length() > 0 ? memberage.substring(2) : "";
    }


    /**
     * 旅行主题
     *
     * @return 旅行主题
     */
    @Transient
    public String getThemes() {
        String themes = "";
        if (themeRelates != null) {
            for (ThemeRelate themeRelate : themeRelates) {
                if (themeRelate.getTheme() != null) {
                    themes += ", " + themeRelate.getTheme().getTheme();
                } else if (themeRelate.getDefineTheme() != null) {
                    themes += ", " + themeRelate.getDefineTheme();
                }
            }
        }
        return themes.length() > 0 ? themes.substring(2) : "";
    }


    /**
     * 必去城市列表
     */
    @Transient
    public String getCityConfs() {
        String cityConfs = "";
        if (cityConfirmeds != null) {
            for (CityConfirmed cityConfirmed : cityConfirmeds) {
                cityConfs += ", " + cityConfirmed.getCity().getCityName();
            }
        }
        return cityConfs.length() > 0 ? cityConfs.substring(2) : "";
    }


    /**
     * 随行人员列表
     */
    @Transient
    public String getAccompanys() {
        String accompanys = "";
        if (accompanyRelates != null) {
            for (AccompanyRelate accompanyRelate : accompanyRelates) {
                accompanys += ", " + accompanyRelate.getAccompanyType();
            }
        }
        return accompanys.length() > 0 ? accompanys.substring(2) : "";
    }

    /**
     * 规划方式列表
     * 获取游客的规划需求，按照时间+城市+是否需要导游的方式显示，采用 “， ”分隔
     */
    @Transient
    public String getCitysPlaneds() {
        String citysPlaneds = "";
        if (cityConfirmeds != null) {

            for (CityConfirmed cityConfirmed : cityConfirmeds) {
                String startDate = DateUtil.format(cityConfirmed.getStartDate(), "yyyy-MM-dd");
                String endDate = DateUtil.format(cityConfirmed.getEndDate(), "yyyy-MM-dd");
                String cityName = cityConfirmed.getCity().getCityName();
                String needGuid = cityConfirmed.getArrangedStatus().equals(0) ? "需要导游" : "不需要导游";
                citysPlaneds += ", " + startDate + " - " + endDate + "  " + cityName + "  " + needGuid;
            }
        }
        return citysPlaneds.length() > 0 ? citysPlaneds.substring(2) : "";
    }


    /**
     * 兴趣城市列表
     */
    @Transient
    public String getCityInts() {
        String cityInts = "";
        if (interestedCityRelates != null) {
            for (InterestedCityRelate interestedCity : interestedCityRelates) {
                cityInts += ", " + interestedCity.getCity().getCityName();
            }
        }
        return cityInts.length() > 0 ? cityInts.substring(2) : "";
    }


    /**
     * 旅游城市
     */
    @Transient
    public String getTravelCitys() {
        String travelCitys = "";
        for (CityConfirmed cityConfirmed : cityConfirmeds) {
            travelCitys += ", " + cityConfirmed.getCity().getCityName();
        }
        for (InterestedCityRelate interestedCityRelate : interestedCityRelates) {
            travelCitys += ", " + interestedCityRelate.getCity().getCityName();
        }
        if (travelCitys.length() > 0) {
            travelCitys = travelCitys.substring(2);
        }
        return travelCitys;
    }

    /**
     * 旅游城市
     */
    @Transient
    public String getInnerTraffs() {
        String innerTraffs = "";
        if (innerCityTrafficRelates != null) {
            for (InnerCityTrafficRelate innerCityTraffic : innerCityTrafficRelates) {
                innerTraffs += ", " + innerCityTraffic.getInnerCityTraffic();

            }
        }

        return innerTraffs.length() > 0 ? innerTraffs.substring(2) : "";
    }

    
    
    
    public BigDecimal getCheckOutAmount()
    {
        return checkOutAmount;
    }


    public void setCheckOutAmount(BigDecimal checkOutAmount)
    {
        this.checkOutAmount = checkOutAmount;
    }


    public Date getCommitOrderTime() {
        return commitOrderTime;
    }


    public void setCommitOrderTime(Date commitOrderTime) {
        this.commitOrderTime = commitOrderTime;
    }


    public City getBackCity() {
		return backCity;
	}


	public void setBackCity(City backCity) {
		this.backCity = backCity;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getTraveler() {
        return traveler;
    }

    public void setTraveler(User traveler) {
        this.traveler = traveler;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getDateConfirm() {
        return dateConfirm;
    }

    public void setDateConfirm(Integer dateConfirm) {
        this.dateConfirm = dateConfirm;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getPersonCount() {
        return personCount;
    }

    public void setPersonCount(Integer personCount) {
        this.personCount = personCount;
    }

    public Integer getHotelRoomCount() {
        return hotelRoomCount;
    }

    public void setHotelRoomCount(Integer hotelRoomCount) {
        this.hotelRoomCount = hotelRoomCount;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public City getStartCity() {
        return startCity;
    }

    public void setStartCity(City startCity) {
        this.startCity = startCity;
    }

    public Integer getConfirmedCityStatus() {
        return confirmedCityStatus;
    }

    public void setConfirmedCityStatus(Integer confirmedCityStatus) {
        this.confirmedCityStatus = confirmedCityStatus;
    }

    public Integer getInterestedCityStatus() {
        return interestedCityStatus;
    }

    public void setInterestedCityStatus(Integer interestedCityStatus) {
        this.interestedCityStatus = interestedCityStatus;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<ThemeRelate> getThemeRelates() {
        return themeRelates;
    }

    public void setThemeRelates(List<ThemeRelate> themeRelates) {
        this.themeRelates = themeRelates;
    }

    public List<CityConfirmed> getCityConfirmeds() {
        return cityConfirmeds;
    }

    public void setCityConfirmeds(List<CityConfirmed> cityConfirmeds) {
        this.cityConfirmeds = cityConfirmeds;
    }

    public List<InterestedCityRelate> getInterestedCityRelates() {
        return interestedCityRelates;
    }

    public void setInterestedCityRelates(List<InterestedCityRelate> interestedCityRelates) {
        this.interestedCityRelates = interestedCityRelates;
    }


    public String getActivityCode() {
        return activityCode;
    }

    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode;
    }

    public Integer getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(Integer activityStatus) {
        this.activityStatus = activityStatus;
    }

    public List<InnerCityTrafficRelate> getInnerCityTrafficRelates() {
        return innerCityTrafficRelates;
    }

    public void setInnerCityTrafficRelates(List<InnerCityTrafficRelate> innerCityTrafficRelates) {
        this.innerCityTrafficRelates = innerCityTrafficRelates;
    }

    public List<PurposeRelate> getPurposeRelates() {
        return purposeRelates;
    }

    public void setPurposeRelates(List<PurposeRelate> purposeRelates) {
        this.purposeRelates = purposeRelates;
    }

    public List<AccompanyRelate> getAccompanyRelates() {
        return accompanyRelates;
    }

    public void setAccompanyRelates(List<AccompanyRelate> accompanyRelates) {
        this.accompanyRelates = accompanyRelates;
    }
    /**
     * 保证金
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private  List<MarginPlan> marginplan;

    public List<MarginPlan> getMarginplan()
    {
        return marginplan;
    }

    public void setMarginplan(List<MarginPlan> marginplan)
    {
        this.marginplan = marginplan;
    }

    public Date getStatusTime() {
        return statusTime;
    }

    public void setStatusTime(Date statusTime) {
        this.statusTime = statusTime;
    }
}