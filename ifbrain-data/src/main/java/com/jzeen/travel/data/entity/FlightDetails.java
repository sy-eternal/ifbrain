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


@Entity
@Table(name="t_flight_details")
@JsonIdentityInfo(generator = JSOGGenerator.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class FlightDetails
{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
    
 
    
    /**
     * 出发日期
     */
    @Column(name = "departure_date")
   /* @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")*/
    private String departureDate;
    
    
    /**
     * 出发时间
     */
    @Column(name = "departure_time")
   /* @DateTimeFormat(pattern = "MM:dd")
    @JsonFormat(pattern = "MM:dd",timezone = "GMT+8")*/
    private String departureTime;
    
    /**
     * 出发城市id
     */
 /* @ManyToOne(targetEntity=City.class)
    @JoinColumn(name ="departure_city_id")
    @JsonBackReference*/
    @Column(name ="departure_city_id")
     private String departureCityId;
    
    /**
     * 出发机场
     */
    @Column(name = "departure_airport_code")
    private String departureAirportCode;
    /**
     * 出发站楼
     */
    @Column(name = "departure_terminal_id")
    private String departureTrminalId;
    
    /**
     * 到达日期
     */
   @Column(name = "arrival_date")
   /* @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")*/
    private String arrivalDate;
    /**
     * 到达时间
     */
    @Column(name = "arrival_time")
   /* @DateTimeFormat(pattern = "MM:dd")
    @JsonFormat(pattern = "MM:dd",timezone = "GMT+8")*/
    private String arrivalTime;
    
    /**
     * 到达城市Id
     */
    
  /*@ManyToOne(targetEntity=City.class)
    @JoinColumn(name ="arrival_city_id")
    @JsonBackReference*/
    
    @Column(name ="arrival_city_id")
    private String arrivalCityId;
    
    /**
     * 到达机场
     */
    @Column(name = "arrival_airport_code")
    private String arrivalAirportCode;
    
    /**
     * 到达航站楼
     */ 
    @Column(name = "arrival_terminal_id")
      private String arrivalTerminalId;
    /**
     * 航班号
     */
    @Column(name = "flight_number")
    private String flightNumber;
    
    /**
     * 级别
     */
    @Column(name = "cabin")
    private String cabin;
    
    /**
     * 机型
     */
    @Column(name = "flight_type")
    private String flightType;
    
    
    /**
     * 飞行时间
     */
    
    @Column(name = "traval_time")
    private String travelTime;
    
    /**
     * 停留时间
     */
    
    @Column(name = "layover_time")
    private String layoverTime;
    
    /**
     * 创建时间
     */
    
    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createTime;
    
    
    @ManyToOne(targetEntity = FilghtPlan.class)
    @JoinColumn(name = "flight_plan_id")
    @JsonBackReference
    private FilghtPlan filghtPlan;
    
    
    

    public String getDepartureCityId()
    {
        return departureCityId;
    }

    public void setDepartureCityId(String departureCityId)
    {
        this.departureCityId = departureCityId;
    }

    public String getArrivalCityId()
    {
        return arrivalCityId;
    }

    public void setArrivalCityId(String arrivalCityId)
    {
        this.arrivalCityId = arrivalCityId;
    }

    public FilghtPlan getFilghtPlan()
    {
        return filghtPlan;
    }

    public void setFilghtPlan(FilghtPlan filghtPlan)
    {
        this.filghtPlan = filghtPlan;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

 


 

    public String getDepartureDate()
    {
        return departureDate;
    }

    public void setDepartureDate(String departureDate)
    {
        this.departureDate = departureDate;
    }

    public String getDepartureTime()
    {
        return departureTime;
    }

    public void setDepartureTime(String departureTime)
    {
        this.departureTime = departureTime;
    }

    public void setArrivalDate(String arrivalDate)
    {
        this.arrivalDate = arrivalDate;
    }

    public void setArrivalTime(String arrivalTime)
    {
        this.arrivalTime = arrivalTime;
    }



    public String getDepartureAirportCode()
    {
        return departureAirportCode;
    }

    public void setDepartureAirportCode(String departureAirportCode)
    {
        this.departureAirportCode = departureAirportCode;
    }

    public String getDepartureTrminalId()
    {
        return departureTrminalId;
    }

    public void setDepartureTrminalId(String departureTrminalId)
    {
        this.departureTrminalId = departureTrminalId;
    }






    public String getArrivalDate()
    {
        return arrivalDate;
    }

    public String getArrivalTime()
    {
        return arrivalTime;
    }



    public String getLayoverTime()
    {
        return layoverTime;
    }

    public String getArrivalAirportCode()
    {
        return arrivalAirportCode;
    }

    public void setArrivalAirportCode(String arrivalAirportCode)
    {
        this.arrivalAirportCode = arrivalAirportCode;
    }

  

    public String getArrivalTerminalId()
    {
        return arrivalTerminalId;
    }

    public void setArrivalTerminalId(String arrivalTerminalId)
    {
        this.arrivalTerminalId = arrivalTerminalId;
    }

    public String getFlightNumber()
    {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber)
    {
        this.flightNumber = flightNumber;
    }

    public String getCabin()
    {
        return cabin;
    }

    public void setCabin(String cabin)
    {
        this.cabin = cabin;
    }

    public String getFlightType()
    {
        return flightType;
    }

    public void setFlightType(String flightType)
    {
        this.flightType = flightType;
    }



    public String getTravelTime()
    {
        return travelTime;
    }

    public void setTravelTime(String travelTime)
    {
        this.travelTime = travelTime;
    }

    public void setLayoverTime(String layoverTime)
    {
        this.layoverTime = layoverTime;
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
