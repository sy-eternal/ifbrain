package com.jzeen.travel.data.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

 

public class AirPlanInfo
{

    /**
     * 单价（美元/人）
     */
    private BigDecimal costPrice;
    /**
     * 航空公司
     * 
     * @return
     */
    private String airline;
    /**
     * 出发城市
     * 
     * @return
     */
    private String fromCity;
    /**
     * 到达城市
     * 
     * @return
     */
    private String toCity;

    /**
     * 出发时间
     * 
     * @return
     */
    private String departureTime;
    /**
     * 到达时间
     * 
     * @return
     */
    private String arrivalTime;

    /**
     * 总飞行时间
     * 
     * @return
     */
    private String totalTime;

    /**
     * 航班号
     * 
     * @return
     */
    private String vehicleNumber;
    /**
     * 机型
     * 
     * @return
     */
    private String airEquipType;
    /**
     * 舱位
     * 
     * @return
     */
    private String rank;

    /**
     * 经停次数
     */

    private Integer lengthSeg;

    /**
     * 停留时间
     * 
     * @return
     */
    private Date layTime;

    /**
     * 出发机场代码
     * 
     * @return
     */
    private String startAirport;
    
    /**
     * 到达机场代码
     * 
     * @return
     */
    private String arriveAirport;
    
    
    /**
     * 出发机场航站楼
     * 
     */
    
    private String departureTerminalId;
    
    
    /**
     * 到达机场航站楼
     */
  private String arrivalTerminalId;
    
    /**
     * 航空公司代码
     * @return
     */
    
    private String airlineCode;
    
    
    
    
    public String getAirlineCode()
    {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode)
    {
        this.airlineCode = airlineCode;
    }

    public String getDepartureTerminalId()
    {
        return departureTerminalId;
    }

    public void setDepartureTerminalId(String departureTerminalId)
    {
        this.departureTerminalId = departureTerminalId;
    }

    public String getArrivalTerminalId()
    {
        return arrivalTerminalId;
    }

    public void setArrivalTerminalId(String arrivalTerminalId)
    {
        this.arrivalTerminalId = arrivalTerminalId;
    }

    private List<AirPlanDetail> airPlanDetailList;
    
    private String summary;
 

    
    

    public String getSummary()
    {
        return summary;
    }

    public void setSummary(String summary)
    {
        this.summary = summary;
    }

    public Integer getLengthSeg()
    {
        return lengthSeg;
    }

    public void setLengthSeg(Integer lengthSeg)
    {
        this.lengthSeg = lengthSeg;
    }

    public String getAirline()
    {
        return airline;
    }

    public void setAirline(String airline)
    {
        this.airline = airline;
    }

    public String getFromCity()
    {
        return fromCity;
    }

    public void setFromCity(String fromCity)
    {
        this.fromCity = fromCity;
    }

    public String getToCity()
    {
        return toCity;
    }

    public void setToCity(String toCity)
    {
        this.toCity = toCity;
    }

    public String getDepartureTime()
    {
        return departureTime;
    }

    public void setDepartureTime(String departureTime)
    {
        this.departureTime = departureTime;
    }

    public String getArrivalTime()
    {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime)
    {
        this.arrivalTime = arrivalTime;
    }

    public String getTotalTime()
    {
        return totalTime;
    }

    public void setTotalTime(String totalTime)
    {
        this.totalTime = totalTime;
    }

    public String getVehicleNumber()
    {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber)
    {
        this.vehicleNumber = vehicleNumber;
    }

    public String getAirEquipType()
    {
        return airEquipType;
    }

    public void setAirEquipType(String airEquipType)
    {
        this.airEquipType = airEquipType;
    }

    public String getRank()
    {
        return rank;
    }

    public void setRank(String rank)
    {
        this.rank = rank;
    }

    public Date getLayTime()
    {
        return layTime;
    }

    public void setLayTime(Date layTime)
    {
        this.layTime = layTime;
    }

    public String getStartAirport()
    {
        return startAirport;
    }

    public void setStartAirport(String startAirport)
    {
        this.startAirport = startAirport;
    }

    public String getArriveAirport()
    {
        return arriveAirport;
    }

    public void setArriveAirport(String arriveAirport)
    {
        this.arriveAirport = arriveAirport;
    }

    public BigDecimal getCostPrice()
    {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice)
    {
        this.costPrice = costPrice;
    }

    public List<AirPlanDetail> getAirPlanDetailList()
    {
        return airPlanDetailList;
    }

    public void setAirPlanDetailList(List<AirPlanDetail> airPlanDetailList)
    {
        this.airPlanDetailList = airPlanDetailList;
    }

    

}
