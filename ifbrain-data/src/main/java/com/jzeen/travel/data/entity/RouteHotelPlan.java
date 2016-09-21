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
@Table(name = "t_route_hotel_plan")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class RouteHotelPlan
{

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

	//线路规划主键
		@ManyToOne(targetEntity = RouteDays.class)
		@JoinColumn(name="route_day_id")
		private RouteDays routeDays;  

		public RouteDays getRouteDays() {
			return routeDays;
		}

		public void setRouteDays(RouteDays routeDays) {
			this.routeDays = routeDays;
		}
    
    /**
     * 酒店活动主键
     */
    @ManyToOne(targetEntity = HotelActivity.class)
    @JoinColumn(name = "hotel_id", updatable = true)
    private HotelActivity hotelActivity;
    
    /**
     * 目的城市
     */
    @ManyToOne(targetEntity = City.class)
    @JoinColumn(name = "city_id")
    private City city;

    /**
     * 入住日期
     */
    @Column(name="check_in_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd ")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date checkInDate;
    /**
     * 退房日期
     */
    @Column(name="check_out_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd ")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date checkOutDate;
    
    /**
     * 酒店英文名
     */
    @Column(name="hotel_eng_name")
    private String hotelEngName;
    
 // 房间类型规划
    @OneToMany(mappedBy = "routeHotelPlan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RouteHotelPlanRoom> hotelPlanRooms;

	/**
     * 酒店中文名
     */
    @Column(name="hotel_cha_name")
    private String hotelChaName;
    
    /**
     * 小计金额
     */
    @Column(name="subtotal_amount")
    private BigDecimal subtotalAmount;
    
    /**
     * 创建时间
     */
    @Column(name="create_time")
    private Date createTime;
    

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public HotelActivity getHotelActivity() {
		return hotelActivity;
	}

	public void setHotelActivity(HotelActivity hotelActivity) {
		this.hotelActivity = hotelActivity;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Date getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}

	public Date getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public String getHotelEngName() {
		return hotelEngName;
	}

	public void setHotelEngName(String hotelEngName) {
		this.hotelEngName = hotelEngName;
	}

	public List<RouteHotelPlanRoom> getHotelPlanRooms() {
		return hotelPlanRooms;
	}

	public void setHotelPlanRooms(List<RouteHotelPlanRoom> hotelPlanRooms) {
		this.hotelPlanRooms = hotelPlanRooms;
	}

	public String getHotelChaName() {
		return hotelChaName;
	}

	public void setHotelChaName(String hotelChaName) {
		this.hotelChaName = hotelChaName;
	}

	public BigDecimal getSubtotalAmount() {
		return subtotalAmount;
	}

	public void setSubtotalAmount(BigDecimal subtotalAmount) {
		this.subtotalAmount = subtotalAmount;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
