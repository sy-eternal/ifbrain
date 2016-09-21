package com.jzeen.travel.data.entity;

import java.math.BigDecimal;
import java.sql.Time;
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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

@Entity
@Table(name="t_route_flight_plan")
@JsonIdentityInfo(generator = JSOGGenerator.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class RouteFilghtPlan {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	//出发日期
	@Column(name="departure_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "HH:mm",timezone = "GMT+8")
	private Date departureDate;

	//出发时间
	@Column(name="departure_time")
	@DateTimeFormat(pattern = "HH:mm")
	@JsonFormat(pattern = "HH:mm",timezone = "GMT+8")
	private Time departureTime;

	//出发城市id
	@Column(name="departure_city_id")
	private Integer departureCityId;

	@Column(name="arrival_city_id")
	private Integer arrivalCityId;

	//到达日期
	@Column(name="arrival_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	private Date arrivalDate;

	//到达时间
	@Column(name="arrival_time")
	@DateTimeFormat(pattern = "HH:mm")
	@JsonFormat(pattern = "HH:mm",timezone = "GMT+8")
	private Time arrivalTime;	  

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

	//人员类型    
	@Column(name="personal_type")
	private Integer personalType;  

	//人数
	@Column(name="personal_count")
	private Integer personalCount; 

	//单位成本
	@Column(name="per_cost")
	private BigDecimal perCost;  

	//单价
	@Column(name="price")
	private BigDecimal price;    

	//小计成本
	@Column(name="subtotal_cost")
	private BigDecimal subtotalCost; 

	//小计金额
	@Column(name="subtotal_amount")
	private BigDecimal subtotalAmount; 

	//供应商id
	@Column(name="supplier_id")
	private Integer supplierId;

	//经停次数
	@Column(name="lengthseg")
	private Integer lengthseg;

	//航程类型
	@Column(name="flight_type")
	private String flightType; 

	//总飞行时间
	@Column(name="total_flight_time")
	private String totalFlightTime; 

	//航空公司代码
	/* @ManyToOne(targetEntity = Airline.class)*/
	@Column(name="airline_code")
	private String airlineCodes;   

	//创建时间
	@Column(name="create_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date createTime;

	@OneToMany(mappedBy = "routeFilghtPlan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<RouteFlightDetails> flightDetails;

	/*
	 * 供应商订单号
	 */
	@Column(name="supplier_num")
	private String supplierNum;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Time getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Time departureTime) {
		this.departureTime = departureTime;
	}

	public Integer getDepartureCityId() {
		return departureCityId;
	}

	public void setDepartureCityId(Integer departureCityId) {
		this.departureCityId = departureCityId;
	}

	public Integer getArrivalCityId() {
		return arrivalCityId;
	}

	public void setArrivalCityId(Integer arrivalCityId) {
		this.arrivalCityId = arrivalCityId;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public Time getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Time arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Integer getPersonalType() {
		return personalType;
	}

	public void setPersonalType(Integer personalType) {
		this.personalType = personalType;
	}

	public Integer getPersonalCount() {
		return personalCount;
	}

	public void setPersonalCount(Integer personalCount) {
		this.personalCount = personalCount;
	}

	public BigDecimal getPerCost() {
		return perCost;
	}

	public void setPerCost(BigDecimal perCost) {
		this.perCost = perCost;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getSubtotalCost() {
		return subtotalCost;
	}

	public void setSubtotalCost(BigDecimal subtotalCost) {
		this.subtotalCost = subtotalCost;
	}

	public BigDecimal getSubtotalAmount() {
		return subtotalAmount;
	}

	public void setSubtotalAmount(BigDecimal subtotalAmount) {
		this.subtotalAmount = subtotalAmount;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	
	public Integer getLengthseg()
    {
        return lengthseg;
    }

    public void setLengthseg(Integer lengthseg)
    {
        this.lengthseg = lengthseg;
    }

    public String getFlightType() {
		return flightType;
	}

	public void setFlightType(String flightType) {
		this.flightType = flightType;
	}

	public String getTotalFlightTime() {
		return totalFlightTime;
	}

	public void setTotalFlightTime(String totalFlightTime) {
		this.totalFlightTime = totalFlightTime;
	}

	public String getAirlineCodes() {
		return airlineCodes;
	}

	public void setAirlineCodes(String airlineCodes) {
		this.airlineCodes = airlineCodes;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<RouteFlightDetails> getFlightDetails() {
		return flightDetails;
	}

	public void setFlightDetails(List<RouteFlightDetails> flightDetails) {
		this.flightDetails = flightDetails;
	}

	public String getSupplierNum() {
		return supplierNum;
	}

	public void setSupplierNum(String supplierNum) {
		this.supplierNum = supplierNum;
	}

}
